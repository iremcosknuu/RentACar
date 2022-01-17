package com.btkAkademi.rentACar.bussiness.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.bussiness.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.bussiness.abstracts.RentalService;
import com.btkAkademi.rentACar.bussiness.constants.Messages;
import com.btkAkademi.rentACar.bussiness.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.bussiness.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.bussiness.requests.carMaintenanceRequests.UpdateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarMaintenanceDao;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao
			, ModelMapperService modelMapperService
			, @Lazy RentalService rentalService) {
		super();
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService=rentalService;
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getAll() {
		List<CarMaintenance> carMaintenanceList = this.carMaintenanceDao.findAll();
		List<CarMaintenanceListDto> response= carMaintenanceList.stream()
				.map(carMaintenance->modelMapperService.forRequest()
				.map(carMaintenance,CarMaintenanceListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response);
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		Result result=businessRules.run(
				checkIfCarIsRented(createCarMaintenanceRequest.getCarId()),
				checkIfCarIsAlreadyInMaintanance(createCarMaintenanceRequest.getCarId())
				);
		
		if(result!=null) {
			return result;
		}
	
		CarMaintenance carMaintenance= modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.carMaintenanceAddded);
		
	}
	
	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest ) {
		Result result = businessRules.run(
				checkIfCarMaintenanceIdExists(updateCarMaintenanceRequest.getId())
				);
		if(result != null) {
			return result;
		}
		
		CarMaintenance carMaintenanceToUpdate = this.carMaintenanceDao.getById(updateCarMaintenanceRequest.getId());
		carMaintenanceToUpdate = modelMapperService.forRequest().map(carMaintenanceToUpdate, CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenanceToUpdate);
		
		return new SuccessResult(Messages.carMaintenanceUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result = businessRules.run(
				checkIfCarMaintenanceIdExists(id)
				);
		if(result != null) {
			return result;
		}
		
		this.carMaintenanceDao.deleteById(id);
		return new SuccessResult(Messages.paymentDeleted);
	}


	@Override
	public boolean IsCarInMaintanance(int id) {
		if(carMaintenanceDao.findByCarIdAndMaintenanceFinishDateIsNull(id)!= null){
			return true;
		}
		return false;
	}

	public Result checkIfCarIsRented(int carId) {
		if(rentalService.isCarRented(carId)) {
			return new ErrorResult(Messages.carRented);
		}
		return new SuccessResult();
	}
	
	public Result checkIfCarMaintenanceIdExists(int id) {
		if(!this.carMaintenanceDao.existsById(id)) {
			return new ErrorResult(Messages.carMaintenanceIdNotExists);
		}
		return new SuccessResult();
	}
	
	private Result checkIfCarIsAlreadyInMaintanance(int carId) {
		if(IsCarInMaintanance(carId)) {
			return new ErrorResult(Messages.carInMaintanance);
		}
		else return new SuccessResult();
	}
	
}
