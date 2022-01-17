package com.btkAkademi.rentACar.bussiness.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.bussiness.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.bussiness.requests.userRequests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.bussiness.requests.userRequests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {
	DataResult<List<IndividualCustomerListDto>> getAll();
	
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	Result delete(int id);
}
