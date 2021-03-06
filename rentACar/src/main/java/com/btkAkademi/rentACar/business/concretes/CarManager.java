package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.plaf.nimbus.NimbusStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDao;
import com.btkAkademi.rentACar.entities.concretes.Car;

import lombok.Data;

@Service
public class CarManager implements CarService{

	private CarDao carDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CarListDto>> getAll(int pageNo,int pageSize) {
		
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		
		List<Car> carList = this.carDao.findAll(pageable).getContent();
		List<CarListDto> response = carList.stream()
				.map(car -> modelMapperService.forDto()
				.map(car,CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}
	
	@Override
	public DataResult<CarListDto> findById(int id) {
		
		if(!this.carDao.existsById(id)) {
			return new ErrorDataResult<CarListDto>(Messages.carIdNotFound);
		}
		
		Car car = this.carDao.getById(id);
		CarListDto response = modelMapperService.forDto().map(car, CarListDto.class);
		return new SuccessDataResult<CarListDto>(response);
	}
	
	@Override
	public DataResult<List<CarListDto>> findByColorId(int colorId) {
		List<Car> carList = this.carDao.findAllByColorId(colorId);
		List<CarListDto> response = carList.stream()
				.map(car -> modelMapperService.forDto()
				.map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}
	
	@Override
	public DataResult<List<CarListDto>> findAllByBrandId(int brandId) {

		List<Car> carList = this.carDao.findAllByBrandId(brandId);
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}
	
	@Override
	public DataResult<List<Integer>> findAvaliableCarsBySegmentId(int segmentId, int cityId) {
		if(carDao.findAvailableCarBySegment(segmentId,cityId).size()<1) {
			return new ErrorDataResult<List<Integer>>();
		}
		return new SuccessDataResult<List<Integer>>(this.carDao.findAvailableCarBySegment(segmentId,cityId));
	}
	
	@Override
	public DataResult<List<CarListDto>> findAllByFuelTypeId(int fuelTypeId) {
		List<Car> carList = this.carDao.findAllByFuelTypeId(fuelTypeId);
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}
	
	@Override
	public Result add(CreateCarRequest createCarRequest) {

		Result result = businessRules.run();
		
		if(result != null) {
			return result;
		}
		
		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult(Messages.carAdded);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Result result=businessRules.run(
				checkIfCarIdExists(updateCarRequest.getId()));
		
		if(result!=null) {
			return result;
		}
		Car carToUpdate = this.carDao.getById(updateCarRequest.getId());
		
		carToUpdate=modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(carToUpdate);
		return new SuccessResult(Messages.carUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result = businessRules.run(
				checkIfCarIdExists(id)
				);
		if(result != null) {
			return result;
		}
		
		this.carDao.deleteById(id);
		return new SuccessResult(Messages.carDeleted);
	}
	
	public Result checkIfCarIdExists(int id) {
		if(!(this.carDao.existsById(id))) {
			return new ErrorResult(Messages.carNotFound);
		}
		return new SuccessResult();
	}


}
