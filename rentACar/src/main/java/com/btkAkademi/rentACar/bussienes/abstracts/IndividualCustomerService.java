package com.btkAkademi.rentACar.bussienes.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.bussienes.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.bussienes.requests.userRequests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.bussienes.requests.userRequests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {
	DataResult<List<IndividualCustomerListDto>> getAll();
	
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	Result delete(int id);
}
