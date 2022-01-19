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

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequests.UpdateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@CrossOrigin
@RestController
@RequestMapping("/api/carmaintenances")
public class CarMaintenancesController {

	private CarMaintenanceService carMaintenanceService;

	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
	}
	
	@GetMapping("getall")
	public DataResult<List<CarMaintenanceListDto>> getAll(){
		return this.carMaintenanceService.getAll();
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest carMaintenanceRequest) {
		return this.carMaintenanceService.add(carMaintenanceRequest);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}
	
	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.carMaintenanceService.delete(id);
	}
}
