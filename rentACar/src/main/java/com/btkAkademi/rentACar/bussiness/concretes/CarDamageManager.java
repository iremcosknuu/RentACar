package com.btkAkademi.rentACar.bussiness.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.bussiness.abstracts.CarDamageService;
import com.btkAkademi.rentACar.bussiness.constants.Messages;
import com.btkAkademi.rentACar.bussiness.dtos.CarDamageListDto;
import com.btkAkademi.rentACar.bussiness.requests.carDamageRequests.CreateCarDamageRequest;
import com.btkAkademi.rentACar.bussiness.requests.carDamageRequests.UpdateCarDamageRequest;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDamageDao;
import com.btkAkademi.rentACar.entities.concretes.Brand;
import com.btkAkademi.rentACar.entities.concretes.CarDamage;

@Service
public class CarDamageManager implements CarDamageService{
	private CarDamageDao carDamageDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService) {
		super();
		this.carDamageDao = carDamageDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public DataResult<List<CarDamageListDto>> getAll() {
		List<CarDamage> carDamageList = this.carDamageDao.findAll();
		List<CarDamageListDto> response = carDamageList.stream()
				.map(carDamage -> modelMapperService.forDto()
				.map(carDamage, CarDamageListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarDamageListDto>>(response);
	}

	@Override
	public Result add(CreateCarDamageRequest createCarDamageRequest) {
		
		System.out.println(createCarDamageRequest.toString());
		CarDamage carDamage = modelMapperService.forRequest()
				.map(createCarDamageRequest,CarDamage.class);
		
		System.out.println(carDamage.toString());
		
	
		this.carDamageDao.save(carDamage);		
		return new SuccessResult(Messages.carDamageAdded);
	}

	@Override
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
		Result result=businessRules.run(
				checkIfCarDamageIdExists(updateCarDamageRequest.getId()));
		
		if(result!=null) {
			return result;
		}
		CarDamage carDamageUpdate = this.carDamageDao.getById(updateCarDamageRequest.getId());
		carDamageUpdate = modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
		
		this.carDamageDao.save(carDamageUpdate);
		
		return new SuccessResult(Messages.carDamageUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result= businessRules.run(
				checkIfCarDamageIdExists(id)
				);
		
		if(result != null) {
			return result;
		}
		
		this.carDamageDao.deleteById(id);
		return new SuccessResult(Messages.carDamageDeleted);
	}
	
	private Result checkIfCarDamageIdExists(int id) {
		if(!this.carDamageDao.existsById(id)) {

			return new ErrorResult(Messages.carDamageIdNotExists);
		}
		return new SuccessResult();
	}
	
}
