package com.btkAkademi.rentACar.bussienes.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.bussienes.dtos.RentalListDto;
import com.btkAkademi.rentACar.bussienes.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.bussienes.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface RentalService {

	DataResult<List<RentalListDto>> getAll(int pageNo,int pageSize);
	DataResult<Rental> findRentalById(int id);
	
	Result add(CreateRentalRequest createRentalRequest);
	Result update(UpdateRentalRequest updateRentalRequest);
	Result delete(int id);
	
	boolean isCarRented(int carId);
}
