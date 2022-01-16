package com.btkAkademi.rentACar.bussienes.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.bussienes.dtos.CarDamageListDto;
import com.btkAkademi.rentACar.bussienes.requests.carDamageRequests.CreateCarDamageRequest;
import com.btkAkademi.rentACar.bussienes.requests.carDamageRequests.UpdateCarDamageRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarDamageService {

	DataResult<List<CarDamageListDto>> getAll();
	
	Result add(CreateCarDamageRequest createCarDamageRequest);
	Result update(UpdateCarDamageRequest updateCarDamageRequest);
	Result delete(int id);
}