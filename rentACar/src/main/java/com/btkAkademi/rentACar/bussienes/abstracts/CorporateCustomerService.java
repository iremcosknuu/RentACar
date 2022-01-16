package com.btkAkademi.rentACar.bussienes.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.bussienes.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.bussienes.requests.userRequests.CorporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.bussienes.requests.userRequests.CorporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CorporateCustomerService {

	DataResult<List<CorporateCustomerListDto>> getAll();
	
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
	Result delete(int id);
}
