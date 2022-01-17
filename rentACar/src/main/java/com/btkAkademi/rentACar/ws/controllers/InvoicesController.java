package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.dtos.InvoiceCorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceIndividualCustomerDto;
import com.btkAkademi.rentACar.business.requests.invoicedRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {

	private InvoiceService invoiceService;

	public InvoicesController(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}
	
	@GetMapping("getAllindividualCustomerInvoice")
	public DataResult<List<InvoiceIndividualCustomerDto>> getAllindividualCustomerInvoice(){
		return this.invoiceService.getAllindividualCustomerInvoice();
	}
	
	@GetMapping("getInvoiceCorporateCustomer")
	public DataResult<List<InvoiceCorporateCustomerDto>> getAllcorporateCustomerInvoice(){
		return this.invoiceService.getAllcorporateCustomerInvoice();
	}
	
	@PostMapping("add")
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		return this.invoiceService.add(createInvoiceRequest);
	}
}
