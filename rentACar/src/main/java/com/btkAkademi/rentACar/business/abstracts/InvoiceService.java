package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.InvoiceCorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceIndividualCustomerDto;
import com.btkAkademi.rentACar.business.requests.invoicedRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface InvoiceService {

	DataResult<List<InvoiceCorporateCustomerDto>> getAllcorporateCustomerInvoice();
	DataResult<List<InvoiceIndividualCustomerDto>> getAllindividualCustomerInvoice();
	
	Result add(CreateInvoiceRequest createInvoiceRequest);
}
