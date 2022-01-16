package com.btkAkademi.rentACar.bussienes.constants;

import java.util.List;

import com.btkAkademi.rentACar.bussienes.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.entities.concretes.City;
import com.btkAkademi.rentACar.entities.concretes.Rental;

public class Messages {
	public static final String invalidBrandName="invalid.brand.name";
	public static final String brandAdded = "brand.added";
	public static final String brandUpdated = "brand.updated";
	public static final String brandNameExists="brand.name.exists";
	public static final String brandLimitExceeded="brand.limit.exceeded";
	public static final String brandNotFound = "brand.not.found";
	
	public static final String colorAdded="color.added";
	public static final String colorNameExists="color.name.exists";
	public static final String updateColor="updated.color";
	public static final String getByIdColor="getById.color";
	public static final String colorNotFound="color.notNotFound";
	public static final String colorDeleted="color.deleted";
	
	public static final String carAdded = "car.added";
	public static final String carUpdated="car.updated";
	public static final String getByIdCar="getById.car";
	public static final String carNotFound="car.carNotFund";
	public static final String carDeleted="car.deleted";
	
	public static final String individualCustomerAdded="individualcustomer.added";
	public static final String individualCustomerAgeNotInLimit="individualcustomer.age.no.limit";
	public static final String individualCustomerExists="individualCustomer.exists";
	public static final String individualCustomerIdNotExists="individualCustomer.id.not.exist";
	public static final String individualCustomerUpdated="individualCustomer.updated";
	public static final String individualCustomerDeleted="individualCustomer.deleted";
	
	public static final String corporateCustomerAdded="corporatecustomer.added";
	public static final String corporateCustomerExists="corporatecustomer.exists";
	public static final String corporateCustomerIdExists="corporateCustomer.id.not.exists";
	public static final String corporateCustomerUpdated="corporatecustomer.updated";
	public static final String corporateCustomerDeleted="corporatecustomer.deleted";
	
	public static final String customerNotFound="customer.not.found";
	
	public static final String rentalAdded="rental.added";
	public static final String returnDateShouldBeAfterTheRentDate = "return.date.should.be.after.the.return.date";
	public static final String returnedKilometerShouldNotBeLowerThanRentedKilometer = "returned.kilometer.should.not.be.lower.than.rented.kilometer";
	public static final String carRented="car.rented";
	public static final String rentalIsNotExists="rental.is.not.exists";
	public static final String rentalIdNotExists="renta.id.not.exist";
	public static final String rentalUpdated="rental.updated";
	public static final String rentalDeleted="rental.deleted";
	
	public static final String carMaintenanceAddded = "carMaintenance.added";
	public static final String carMaintenanceIdNotExists = "carMaintenance.id.not.exists";
	public static final String carMaintenanceUpdated = "carMaintenance.updated";
	
	public static final String carIdNotFoundAndReturnDateNotNull="carId.not.found.and.return.date.not.null";
	public static final String carIdFoundAndReturnDate="carId.found.and.return.date";
	public static final String carInMaintanance="car.in.maintanance";
	
	public static final String additionalServiceAdded="additionalService.added";
	public static final String additionalServiceInRentalIdNotFound="additionalService.in.rentalId.not.found";
	public static final String additionalServiceUpdated="additionalService.updated";
	public static final String additionalServiceDeleted="additionalService.deleted";
	public static final String additionalServiceNotExist="additionalService.not.exist";
	
	public static final String cityAdded="city.added";
	public static final String cityNotFound="city.not.found";
	public static final String cityNameExists="cityName.exists";
	public static final String cityIdExist="cityId.not.exist";
	public static final String cityIdNotExist="cityId.not.exist";
	public static final String cityDeleted="city.deleted";
	public static final String cityUpdated="city.updated";
	
	public static final String limitNotEnough="limit.not.enough";
	public static final String brandDeleted="brand.deletd";

	public static final String carDamageIdNotExists="carDamageId.not.exists";
	public static final String carDamageUpdated="carDamage.updated";
	public static final String carDamageAdded="carDamage.added";
	public static final String carDamageDeleted="carDamage.deleted";

	public static final String paymentAdded="payment.added";
	public static final String paymentIdNotExists="payment.updated";
	public static final String paymentUpdated="payment.updated";
	public static final String paymentDeleted="payment.deleted";


}
