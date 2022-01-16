package com.btkAkademi.rentACar.bussienes.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.bussienes.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.bussienes.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.bussienes.requests.carMaintenanceRequests.UpdateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarMaintenanceService {
	DataResult<List<CarMaintenanceListDto>> getAll();
	
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);
	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);
	Result delete(int id);
	
	boolean IsCarInMaintanance (int carId) ;
}
