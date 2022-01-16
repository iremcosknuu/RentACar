package com.btkAkademi.rentACar.bussienes.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.bussienes.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.bussienes.constants.Messages;
import com.btkAkademi.rentACar.bussienes.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.bussienes.requests.userRequests.CorporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.bussienes.requests.userRequests.CorporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{

	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		super();
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CorporateCustomerListDto>> getAll() {
		List<CorporateCustomer> corporateCustomerList = this.corporateCustomerDao.findAll();
		List<CorporateCustomerListDto> response = corporateCustomerList.stream()
				.map(corporateCustomer-> modelMapperService. forDto()
				.map(corporateCustomer, CorporateCustomerListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateCustomerListDto>>(response);
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		Result result= businessRules.run(
				checkIfCompanyNameExists(createCorporateCustomerRequest.getCompanyName()));
		
		if(result!=null) {
			return result;
		}
		
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.corporateCustomerAdded);
	}
	
	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		Result result= businessRules.run(
				checkIfCompanyNameExists(updateCorporateCustomerRequest.getCompanyName()),
				checkIfCorporateCustomerIdExists(updateCorporateCustomerRequest.getId())
				);
		
		if(result!=null) {
			return result;
		}
		
		CorporateCustomer corporateCustomerToUpdate = this.corporateCustomerDao.getById(updateCorporateCustomerRequest.getId());
		corporateCustomerToUpdate = modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
		
		return new SuccessResult(Messages.corporateCustomerUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result= businessRules.run(
				checkIfCorporateCustomerIdExists(id)
				);
		
		if(result!=null) {
			return result;
		}
		
		this.corporateCustomerDao.deleteById(id);
		return new SuccessResult(Messages.corporateCustomerUpdated);
	}
	
	private Result checkIfCompanyNameExists(String name) {
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.findByCompanyName(name);
		if(corporateCustomer!=null) {
			return new ErrorResult(Messages.corporateCustomerExists);
		}
		return new SuccessResult();
	}

	private Result checkIfCorporateCustomerIdExists(int id) {
		if (!this.corporateCustomerDao.existsById(id)) {
			return new ErrorResult(Messages.corporateCustomerIdExists);
		} else
			return new SuccessResult();
	}


}
