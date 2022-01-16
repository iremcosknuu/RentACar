package com.btkAkademi.rentACar.bussienes.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.bussienes.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.bussienes.abstracts.RentalService;
import com.btkAkademi.rentACar.bussienes.constants.Messages;
import com.btkAkademi.rentACar.bussienes.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.bussienes.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.bussienes.requests.additionalServiceRequests.UpdateAddionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService{

	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService,
			RentalService rentalService) {
		super();
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}
	
	@Override
	public DataResult<List<AdditionalServiceListDto>> getAll() {
		List<AdditionalService> additionalServiceList = this.additionalServiceDao.findAll();
		List<AdditionalServiceListDto> response = additionalServiceList.stream()
				.map(additionalService -> modelMapperService.forDto()
				.map(additionalService, AdditionalServiceListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<AdditionalServiceListDto>>(response);
	}

	/*@Override
	public DataResult<List<AdditionalServiceListDto>> findAllByRentalId(int rentalId) {
		if(!this.additionalServiceDao.existsById(rentalId)) {
			return new ErrorDataResult<List<AdditionalServiceListDto>>(Messages.additionalServiceInRentalIdNotFound);
		}
		
		List<AdditionalService> additionalServiceList= this.additionalServiceDao.findAllByIdRentalId(rentalId);
		List<AdditionalServiceListDto> response = additionalServiceList.stream()
				.map(additionalService -> modelMapperService.forDto()
				.map(additionalService, AdditionalServiceListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<AdditionalServiceListDto>>(response);
	}*/

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		Result result=businessRules.run(
				checkIfRentalIsExists(createAdditionalServiceRequest.getRentalId()));
		if(result!= null) {
			return result;
		}
		
		AdditionalService additionalService =modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.additionalServiceAdded);
	}
	
	@Override
	public Result update(UpdateAddionalServiceRequest updateAddionalServiceRequest) {
		Result result = businessRules.run(
				checkIfRentalIsExists(updateAddionalServiceRequest.getRentalId()));
		
		if (result!=null) {
			return result;
		}
		AdditionalService additionalServiceToUpdate = this.additionalServiceDao.getById(updateAddionalServiceRequest.getId());
		additionalServiceToUpdate = modelMapperService.forRequest().map(updateAddionalServiceRequest, AdditionalService.class);

		this.additionalServiceDao.save(additionalServiceToUpdate);
		return new SuccessResult(Messages.additionalServiceUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result = businessRules.run(
				checkIfAdditionalServiceIdExist(id));
		
		
		if(result!= null){
			return result;
		}
		
		additionalServiceDao.deleteById(id);
		return new SuccessResult(Messages.additionalServiceDeleted);
	}
	
	private Result checkIfRentalIsExists(int rentalId) {
		if(!rentalService.findRentalById(rentalId).isSuccess()) {
			return new ErrorResult(Messages.rentalIsNotExists);
		}
		return new SuccessResult();
	}
	
	private Result checkIfAdditionalServiceIdExist(int id) {
		if (!additionalServiceDao.existsById(id)) {
			return new ErrorResult(Messages.additionalServiceNotExist);
		}

		return new SuccessResult();

	}


}
