package com.btkAkademi.rentACar.bussienes.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.bussienes.dtos.PaymentListDto;
import com.btkAkademi.rentACar.bussienes.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.bussienes.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PaymentService {
	DataResult<List<PaymentListDto>> getAll();
	
	Result add(CreatePaymentRequest createPaymentRequest);
	Result update(UpdatePaymentRequest updatePaymentRequest);
	Result delete(int id);
}
