package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.FuelTypeService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.FuelTypeListDto;
import com.btkAkademi.rentACar.business.requests.fuelTypeRequests.CreateFuelTypeRequest;
import com.btkAkademi.rentACar.business.requests.fuelTypeRequests.UpdateFuelTypeRequest;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.FuelTypeDao;
import com.btkAkademi.rentACar.entities.concretes.FuelType;

@Service
public class FuelTypeManager implements FuelTypeService {
	
	private FuelTypeDao fuelTypeDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public FuelTypeManager(FuelTypeDao fuelTypeDao, ModelMapperService modelMapperService) {
		super();
		this.fuelTypeDao = fuelTypeDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<FuelTypeListDto>> getAll() {
		List<FuelType> fuelTypeList = this.fuelTypeDao.findAll();
		List<FuelTypeListDto> response = fuelTypeList.stream()
				.map(fuelType -> modelMapperService.forDto()
				.map(fuelType, FuelTypeListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<FuelTypeListDto>>(response);
	}

	@Override
	public Result add(CreateFuelTypeRequest createFuelTypeRequest) {
		Result result= businessRules.run(
				);
		if(result != null) {
			return result;
		}
		
		FuelType fuelType = modelMapperService.forRequest().map(createFuelTypeRequest, FuelType.class);
		this.fuelTypeDao.save(fuelType);
		return new SuccessResult(Messages.fuelTypeAdded);
	}

	@Override
	public Result update(UpdateFuelTypeRequest updateFuelTypeRequest) {
		Result result = businessRules.run(
				checkIfFuelTypeIdExists(updateFuelTypeRequest.getId())
				);
		if(result != null) {
			return result;
		}
		
		FuelType fuelTypeUpdate = this.fuelTypeDao.getById(updateFuelTypeRequest.getId());
		fuelTypeUpdate = modelMapperService.forRequest().map(updateFuelTypeRequest, FuelType.class);
		this.fuelTypeDao.save(fuelTypeUpdate);
		return new SuccessResult(Messages.fuelTypeUpdated);
	}
	
	@Override
	public Result delete(int id) {
		Result result = businessRules.run(
				checkIfFuelTypeIdExists(id)
				);
		if(result != null) {
			return result;
		}
		
		this.fuelTypeDao.deleteById(id);
		return new SuccessResult(Messages.fuelTypeDeleted);
	}
	
	private Result checkIfFuelTypeIdExists(int id) {
		if(!this.fuelTypeDao.existsById(id)) {
			return new ErrorResult(Messages.fuelTypeNotFound);
		}
		
		return new SuccessResult();
	}



}
