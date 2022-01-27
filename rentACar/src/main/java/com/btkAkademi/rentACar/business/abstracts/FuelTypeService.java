package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.FuelTypeListDto;
import com.btkAkademi.rentACar.business.requests.fuelTypeRequests.CreateFuelTypeRequest;
import com.btkAkademi.rentACar.business.requests.fuelTypeRequests.UpdateFuelTypeRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface FuelTypeService {
	DataResult<List<FuelTypeListDto>> getAll();
	
	Result add(CreateFuelTypeRequest createFuelTypeRequest);
	Result update(UpdateFuelTypeRequest updateFuelTypeRequest);
	Result delete(int id);
}
