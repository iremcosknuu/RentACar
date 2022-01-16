package com.btkAkademi.rentACar.bussienes.abstracts;

import java.util.List;
import com.btkAkademi.rentACar.bussienes.dtos.CarListDto;
import com.btkAkademi.rentACar.bussienes.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.bussienes.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarService {
	DataResult<List<CarListDto>> getAll(int pageNo,int pageSize);
	DataResult<CarListDto> findByCarId(int id);
	
	Result add(CreateCarRequest createCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	Result delete(int id);
}
