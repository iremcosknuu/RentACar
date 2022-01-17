package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.InvoiceCorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceIndividualCustomerDto;
import com.btkAkademi.rentACar.business.requests.invoicedRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.InvoiceDao;
import com.btkAkademi.rentACar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService{

	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;
	
	public InvoiceManager(InvoiceDao invoiceDao, 
			ModelMapperService modelMapperService
			) {
		super();
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<InvoiceCorporateCustomerDto>> getAllcorporateCustomerInvoice() {
		List<Invoice> invoiceCorporateCustomers = this.invoiceDao.findAll();
		List<InvoiceCorporateCustomerDto> response = invoiceCorporateCustomers.stream()
						.map(invoice -> modelMapperService.forRequest()
						.map(invoice, InvoiceCorporateCustomerDto.class))
						.collect(Collectors.toList());
		return new SuccessDataResult<List<InvoiceCorporateCustomerDto>>(response);
	}

	@Override
	public DataResult<List<InvoiceIndividualCustomerDto>> getAllindividualCustomerInvoice() {
		List<Invoice> invoiceIndividualCustomers = this.invoiceDao.findAll();
		List<InvoiceIndividualCustomerDto> response = invoiceIndividualCustomers.stream()
						.map(invoice -> modelMapperService.forRequest()
						.map(invoice, InvoiceIndividualCustomerDto.class))
						.collect(Collectors.toList());
		return new SuccessDataResult<List<InvoiceIndividualCustomerDto>>(response);
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Result result= businessRules.run(checkIfInvoicedAlreadExists(createInvoiceRequest.getRentalId()));
		if(result != null) {
			return result;
		}
		
		Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.invoiceAdded);
	}
	
	private Result checkIfInvoicedAlreadExists(int rentalId) {
		if(this.invoiceDao.findByRentalId(rentalId) != null) {
			return new ErrorResult();
		}
		return new SuccessResult(Messages.invoiceRentalIsAlreadyExist);
	}
	
	
	
}
