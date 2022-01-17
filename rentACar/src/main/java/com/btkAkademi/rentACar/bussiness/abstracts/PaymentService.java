package com.btkAkademi.rentACar.bussiness.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.bussiness.dtos.PaymentListDto;
import com.btkAkademi.rentACar.bussiness.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.bussiness.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PaymentService {
	DataResult<List<PaymentListDto>> getAll();
	
	Result add(CreatePaymentRequest createPaymentRequest);
	Result update(UpdatePaymentRequest updatePaymentRequest);
	Result delete(int id);
}
