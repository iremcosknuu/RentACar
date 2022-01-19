package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.UpdateAddionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@CrossOrigin
@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServicesController {

	private AdditionalServiceService additionalServiceService;

	@Autowired
	public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
		super();
		this.additionalServiceService = additionalServiceService;
	}
	
	@GetMapping("getAll")
	public DataResult<List<AdditionalServiceListDto>> getAll(){
		return this.additionalServiceService.getAll();
	}
	
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		return this.additionalServiceService.add(createAdditionalServiceRequest);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateAddionalServiceRequest updateAddionalServiceRequest) {
		return this.additionalServiceService.update(updateAddionalServiceRequest);
	}
	
	@DeleteMapping("delete/{id}")
	public Result deleet(@PathVariable int id) {
		return this.additionalServiceService.delete(id);
	}
}
