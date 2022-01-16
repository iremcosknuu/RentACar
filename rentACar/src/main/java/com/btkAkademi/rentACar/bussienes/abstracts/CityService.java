package com.btkAkademi.rentACar.bussienes.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.bussienes.dtos.CityListDto;
import com.btkAkademi.rentACar.bussienes.requests.cityRequests.CreateCityRequest;
import com.btkAkademi.rentACar.bussienes.requests.cityRequests.UpdateCityRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.City;

public interface CityService {

	DataResult<List<CityListDto>> getAll();
	DataResult<CityListDto> findCityById(int id);
	
	Result add(CreateCityRequest createCityRequest);
	Result update(UpdateCityRequest updateCityRequest);
	Result delete(int id);
}
