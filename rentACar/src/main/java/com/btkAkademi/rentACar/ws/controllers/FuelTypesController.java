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

import com.btkAkademi.rentACar.business.abstracts.FuelTypeService;
import com.btkAkademi.rentACar.business.dtos.FuelTypeListDto;
import com.btkAkademi.rentACar.business.requests.fuelTypeRequests.CreateFuelTypeRequest;
import com.btkAkademi.rentACar.business.requests.fuelTypeRequests.UpdateFuelTypeRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/fueltypes")
@CrossOrigin
public class FuelTypesController {
	
	private FuelTypeService fuelTypeService;

	public FuelTypesController(FuelTypeService fuelTypeService) {
		super();
		this.fuelTypeService = fuelTypeService;
	}
	
	@GetMapping("getall")
	public DataResult<List<FuelTypeListDto>> getAll(){
		return this.fuelTypeService.getAll();
	}

	@PostMapping("add")
	public Result add(CreateFuelTypeRequest createFuelTypeRequest) {
		return this.fuelTypeService.add(createFuelTypeRequest);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateFuelTypeRequest updateFuelTypeRequest) {
		return this.fuelTypeService.update(updateFuelTypeRequest);
	}
	
	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.fuelTypeService.delete(id);
	}
}
