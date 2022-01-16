package com.btkAkademi.rentACar.bussienes.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.bussienes.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.bussienes.constants.Messages;
import com.btkAkademi.rentACar.bussienes.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.bussienes.requests.userRequests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.bussienes.requests.userRequests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,
			ModelMapperService modelMapperService) {
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<IndividualCustomerListDto>> getAll() {
		List<IndividualCustomer> individualCustomerList = this.individualCustomerDao.findAll();
		List<IndividualCustomerListDto> response = individualCustomerList.stream()
				.map(individualCustomer -> modelMapperService.forDto()
				.map(individualCustomer,IndividualCustomerListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualCustomerListDto>>(response);
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		Result result=businessRules.run(
				checkIfEmailExists(createIndividualCustomerRequest.getEmail())
				,checkIfBirthDateLimit(createIndividualCustomerRequest.getBirthDate(),18));
		
		if(result!=null) {
			return result;
		}

		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.individualCustomerAdded);
	}
	
	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		Result result=businessRules.run(
				checkIfEmailExists(updateIndividualCustomerRequest.getEmail()),
				checkIfIndividualCustomerIdExists(updateIndividualCustomerRequest.getId()));
		
		if(result!=null) {
			return result;
		}
		
		IndividualCustomer individualCustomerToUpdate = this.individualCustomerDao.getById(updateIndividualCustomerRequest.getId());
		individualCustomerToUpdate=modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomerToUpdate);
		
		return new SuccessResult(Messages.individualCustomerUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result=businessRules.run(
				checkIfIndividualCustomerIdExists(id)
				);
		
		if(result!=null) {
			return result;
		}
		
		this.individualCustomerDao.deleteById(id);
		return null;
	}

	
	private Result checkIfEmailExists(String email) {
		IndividualCustomer individualCustomer = this.individualCustomerDao.findByEmail(email);
		if(individualCustomer!=null) {
			return new ErrorResult(Messages.individualCustomerExists);
		}
		return new SuccessResult();
	}
	
	private Result checkIfBirthDateLimit(LocalDate birthDate,int ageLimit) {
		int age=Period.between(birthDate, LocalDate.now()).getYears();
		System.out.println(LocalDate.now());
		if(age<ageLimit) {
			return new ErrorResult(Messages.individualCustomerAgeNotInLimit);
		}
		return new SuccessResult();

	}
	
	public Result checkIfIndividualCustomerIdExists(int id) {
		if(this.individualCustomerDao.existsById(id)) {
			return new ErrorResult(Messages.individualCustomerIdNotExists);
		}
		return new SuccessResult();
	}

	
}
