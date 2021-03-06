package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.adapters.kKB.abstracts.FindexAdaperService;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalDao;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;
	private CarMaintenanceService carMaintenanceService;
	private CityService cityService;
	private FindexAdaperService findexAdaperService;
	private CarService carService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	
	public RentalManager(RentalDao rentalDao
			, ModelMapperService modelMapperService
			, CustomerService customerService
			,@Lazy CarMaintenanceService carMaintenanceService
			,CityService cityService
			,FindexAdaperService findexAdaperService
			,CarService carService
			,IndividualCustomerService individualCustomerService) {
		super();
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.customerService=customerService;
		this.carMaintenanceService=carMaintenanceService;
		this.cityService=cityService;
		this.findexAdaperService = findexAdaperService;
		this.carService = carService;
		this.individualCustomerService=individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
	}

	@Override
	public DataResult<List<RentalListDto>> getAll(int pageNo, int pageSize) {
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		List<Rental> rentalList = this.rentalDao.findAll(pageable).getContent();
		List<RentalListDto> response = rentalList.stream()
				.map(rental -> modelMapperService.forDto()
				.map(rental,RentalListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<RentalListDto>>(response);
	}

	@Override
	public DataResult<RentalListDto> findById(int id) {
		if(!this.rentalDao.existsById(id)) {
			return new ErrorDataResult<RentalListDto>(Messages.rentalIdNotExists);
		}
		RentalListDto response = modelMapperService.forDto().map(rentalDao.findById(id).get(), RentalListDto.class);
		return new SuccessDataResult<RentalListDto>(response);		
	}
	
	@Override
	public DataResult<RentalListDto> addForCorporateCustomer(CreateRentalRequest createRentalRequest) {
		Result result = businessRules.run(
				checkIfDatesCorrect(createRentalRequest.getRentDate(), createRentalRequest.getReturnDate()),
				checkIfKilometerCorrect(createRentalRequest.getRentedKilometer(),createRentalRequest.getReturnedKilometer()),
				checkIfCustomerExist(createRentalRequest.getCustomerId()),
				checkIfCarInMaintanance(createRentalRequest.getCarId()),
				checkIfCityExist(createRentalRequest.getPickUpCityId()),
				checkIfCityExist(createRentalRequest.getReturnCityId())
			//	checkIfFindexsScoreIsEnough(createRentalRequest.getCarId(), corporateCustomerService.findById(createRentalRequest.getCustomerId()).getData().getTaxnumber()),
			//	checkIfCustomerAgeIsEnough(createRentalRequest.getCarId(), createRentalRequest.getCustomerId())
				);

		if (result != null) {
			return new ErrorDataResult<RentalListDto>(result.getMessage());
		}
		
		Rental rental= modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		RentalListDto rentalListDto = modelMapperService.forDto().map(rental, RentalListDto.class);
		return new SuccessDataResult<RentalListDto>(rentalListDto,Messages.rentalAdded);
	}
	
	@Override
	public DataResult<RentalListDto> addForIndividualCustomer(CreateRentalRequest createRentalRequest) {
		
		Result result = businessRules.run(
				checkIfDatesCorrect(createRentalRequest.getRentDate(), createRentalRequest.getReturnDate()),
				checkIfKilometerCorrect(createRentalRequest.getRentedKilometer(),createRentalRequest.getReturnedKilometer()),
				checkIfCustomerExist(createRentalRequest.getCustomerId()),
				checkIfCarInMaintanance(createRentalRequest.getCarId()),
				checkIfCityExist(createRentalRequest.getPickUpCityId()),
				checkIfCityExist(createRentalRequest.getReturnCityId())
				//checkIfFindexsScoreIsEnough(createRentalRequest.getCarId(), individualCustomerService.findById(createRentalRequest.getCustomerId()).getData().getNationalityId()),
				//checkIfCustomerAgeIsEnough(createRentalRequest.getCarId(), createRentalRequest.getCustomerId())
				);

		if (result != null) {
			return new ErrorDataResult<RentalListDto>(result.getMessage());
		}
		
		Rental rental= modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		
		RentalListDto rentalListDto = modelMapperService.forDto().map(rental, RentalListDto.class);
		return new SuccessDataResult<RentalListDto>(rentalListDto,Messages.rentalAdded);
	}
	
	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Result result = businessRules.run(checkIfDatesCorrect(updateRentalRequest.getRentDate(), updateRentalRequest.getReturnDate()),
				checkIfKilometerCorrect(updateRentalRequest.getRentedKilometer(),updateRentalRequest.getReturnedKilometer()),
				checkIfCustomerExist(updateRentalRequest.getCustomerId()),
				checkIfCarInMaintanance(updateRentalRequest.getCarId()),
				checkIfCityExist(updateRentalRequest.getPickUpCityId()),
				checkIfCityExist(updateRentalRequest.getReturnCityId()),
				checkIfRentalIdExists(updateRentalRequest.getId()));
		if (result != null) {
			return result;
		}
		
		Rental rentalToUpdate = this.rentalDao.getById(updateRentalRequest.getId());
		rentalToUpdate = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rentalToUpdate);
		
		return new SuccessResult();
	}

	@Override
	public Result delete(int id) {
		Result result = businessRules.run(
				checkIfRentalIdExists(id));
		if (result != null) {
			return result;
		}
		
		this.rentalDao.deleteById(id);
		return new SuccessResult(Messages.rentalDeleted);
	}

	@Override
	public boolean isCarRented(int carId) {
		if(rentalDao.findByCarIdAndReturnDateIsNull(carId) != null) {
			return true;
		}
		return false;
	}
	
	private Result checkIfDatesCorrect(LocalDate rentDate, LocalDate returnDate) {
		if (!returnDate.isAfter(rentDate)) {
			return new ErrorResult(Messages.returnDateShouldBeAfterTheRentDate);
		}
		return new SuccessResult();
	}

	private Result checkIfKilometerCorrect(int rentedKilometer, int returnedKilometer) {
		if (rentedKilometer > returnedKilometer) {
			return new ErrorResult(Messages.returnedKilometerShouldNotBeLowerThanRentedKilometer);
		}
		return new SuccessResult();
	}

	private Result checkIfCustomerExist(int customerId) {
		if (!customerService.findCustomerById(customerId).isSuccess()) {
			return new ErrorResult(Messages.customerNotFound);
		}
		return new SuccessResult();
	}
	
	private Result checkIfCarInMaintanance(int carId) {
		if(carMaintenanceService.IsCarInMaintanance(carId)) {
			return new ErrorResult(Messages.carInMaintanance);
		}
		return new SuccessResult();
	}
	private Result checkIfCityExist(int cityId){
		if(!this.cityService.findById(cityId).isSuccess()) {
			return new ErrorResult();
		}
		return new SuccessResult();
	}	
	
	private Result checkIfRentalIdExists(int id) {
		if(!this.rentalDao.existsById(id)) {
			return new ErrorResult(Messages.rentalIdNotExists);
		}
		return new SuccessResult();
	}
	
	/*private Result checkIfFindexsScoreIsEnough(int carId,String customerId){
		
		int customerFindexScore =  this.findexAdaperService.checkIfKkbLimitIndividualCustomer(customerId);
		int carFindexScore = this.carService.findById(carId).getData().getFindexScore();
		
		if( customerFindexScore < carFindexScore) {
			return new ErrorResult(Messages.findexScoreIsNotEnough);
		}
		return new SuccessResult();
	}*/
	/*
	private Result checkIfCustomerAgeIsEnough(int carId, int customerId) {
		int age = Period.between(individualCustomerService.findById(customerId).getData().getBirthDate(), LocalDate.now()).getYears();
		int minAge = this.carService.findById(carId).getData().getMinAge();
		
		if(age<minAge) {
			return new ErrorResult(Messages.customerAgeNotEnough);
		}
		
		return new SuccessResult();
	}
	*/
	
	private DataResult<CarListDto> findAvailableCar(int segmentId,int cityId){
		if(carService.findAvaliableCarsBySegmentId(segmentId, cityId).isSuccess()) {
			CarListDto car = carService.findById(carService.findAvaliableCarsBySegmentId(segmentId, cityId).getData().get(0)).getData();
			return new SuccessDataResult<CarListDto>(car);
		}else return new ErrorDataResult<CarListDto>();
	}

}
