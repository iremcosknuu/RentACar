package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.bussienes.abstracts.PaymentService;
import com.btkAkademi.rentACar.bussienes.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.bussienes.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {
	
	private PaymentService paymentService;

	@Autowired
	public PaymentsController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreatePaymentRequest createPaymentRequest) {
		return this.paymentService.add(createPaymentRequest);
	}
	
	@PutMapping("update")
	public Result add(@RequestBody @Valid UpdatePaymentRequest updatePaymentRequest) {
		return this.paymentService.update(updatePaymentRequest);
	}
	
	@DeleteMapping("delete/{id}")
	public Result add(@PathVariable int id) {
		return this.paymentService.delete(id);
	}
}
