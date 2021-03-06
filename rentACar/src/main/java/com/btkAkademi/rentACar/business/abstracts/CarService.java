package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarService {
	DataResult<List<CarListDto>> getAll(int pageNo,int pageSize);
	DataResult<CarListDto> findById(int id);
	DataResult<List<CarListDto>> findByColorId(int colorid);
	DataResult<List<CarListDto>> findAllByBrandId(int brandId);
	DataResult<List<Integer>>  findAvaliableCarsBySegmentId(int segmentId, int city);
	DataResult<List<CarListDto>> findAllByFuelTypeId(int segmentId);

	
	Result add(CreateCarRequest createCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	Result delete(int id);
}
