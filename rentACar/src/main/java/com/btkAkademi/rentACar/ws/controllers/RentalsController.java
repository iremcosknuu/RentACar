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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.bussienes.abstracts.RentalService;
import com.btkAkademi.rentACar.bussienes.dtos.RentalListDto;
import com.btkAkademi.rentACar.bussienes.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.bussienes.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;



@RestController
@RequestMapping("/api/rentals")
public class RentalsController {

	private RentalService rentalService;

	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	
	@GetMapping("getall")
	public DataResult<List<RentalListDto>> getAll(@RequestParam int pageNo,@RequestParam int pageSize){
		return rentalService.getAll(pageNo,pageSize);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
		return rentalService.add(createRentalRequest);
		
	}
	
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
		return rentalService.update(updateRentalRequest);
		
	}
	
	@DeleteMapping("delete/{id}")
	public Result update(@PathVariable int id) {
		return rentalService.delete(id);
		
	}
}