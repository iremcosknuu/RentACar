package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface RentalService {

	DataResult<List<RentalListDto>> getAll(int pageNo,int pageSize);
	DataResult<RentalListDto> findById(int id);
	
	
	DataResult<RentalListDto> addForCorporateCustomer(CreateRentalRequest createRentalRequest);
	DataResult<RentalListDto> addForIndividualCustomer(CreateRentalRequest createRentalRequest);
	Result update(UpdateRentalRequest updateRentalRequest);
	Result delete(int id);
	
	boolean isCarRented(int carId);
}
