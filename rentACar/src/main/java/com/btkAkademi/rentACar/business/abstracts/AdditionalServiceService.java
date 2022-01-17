package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.UpdateAddionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface AdditionalServiceService {
	
	DataResult<List<AdditionalServiceListDto>> getAll();
	DataResult<List<AdditionalServiceListDto>> findAllByRentalId(int rentalId);
	
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	Result update(UpdateAddionalServiceRequest updateAddionalServiceRequest);
	Result delete(int id);
}
