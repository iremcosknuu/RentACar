package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.bussiness.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.bussiness.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.bussiness.requests.userRequests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.bussiness.requests.userRequests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/individualCustomers")
public class IndividualCustomersController {
	private IndividualCustomerService individualCustomerService;

	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService; 
	};
	
	
	@GetMapping("getall")
	public DataResult<List<IndividualCustomerListDto>> add() {
		return individualCustomerService.getAll();
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		return individualCustomerService.add(createIndividualCustomerRequest);
	}
	
	@PutMapping("update")
	public Result add(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		return individualCustomerService.update(updateIndividualCustomerRequest);
	}
	
	@DeleteMapping("delete/{id}")
	public Result add(@PathVariable int id) {
		return individualCustomerService.delete(id);
	}
}
