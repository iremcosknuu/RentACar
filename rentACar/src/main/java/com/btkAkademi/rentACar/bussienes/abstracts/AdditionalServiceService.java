package com.btkAkademi.rentACar.bussienes.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.bussienes.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.bussienes.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.bussienes.requests.additionalServiceRequests.UpdateAddionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface AdditionalServiceService {
	
	DataResult<List<AdditionalServiceListDto>> getAll();
	//DataResult<List<AdditionalServiceListDto>> findAllByRentalId(int rentalId);
	
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	Result update(UpdateAddionalServiceRequest updateAddionalServiceRequest);
	Result delete(int id);
}
