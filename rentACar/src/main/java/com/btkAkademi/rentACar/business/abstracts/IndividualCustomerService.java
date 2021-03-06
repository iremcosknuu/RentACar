package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.userRequests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.userRequests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {
	DataResult<List<IndividualCustomerListDto>> getAll();
	DataResult<IndividualCustomerListDto> findById(int id);
	
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	Result delete(int id);
}
