package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.business.requests.userRequests.CorporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.userRequests.CorporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@CrossOrigin
@RestController
@RequestMapping("/api/corporteCustomers")
public class CorporateCustomersController {

	private CorporateCustomerService corporateCustomerService;
	
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		super();
		this.corporateCustomerService = corporateCustomerService;
	}

	@GetMapping("getall")
	public DataResult<List<CorporateCustomerListDto>> getAll(){
		return this.corporateCustomerService.getAll();
	}
	
	@GetMapping("findById/{id}")
	public DataResult<CorporateCustomerListDto> findById(int id){
		return this.corporateCustomerService.findById(id);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCorporateCustomerRequest corporateCustomerRequest) {
		return this.corporateCustomerService.add(corporateCustomerRequest);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}
	
	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.corporateCustomerService.delete(id);
	}
	
}
