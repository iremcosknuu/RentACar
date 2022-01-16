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

import com.btkAkademi.rentACar.bussienes.abstracts.BrandService;
import com.btkAkademi.rentACar.bussienes.dtos.BrandListDto;
import com.btkAkademi.rentACar.bussienes.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.bussienes.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	private BrandService brandService;

	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@GetMapping("getall")
	public DataResult<List<BrandListDto>>getAll(){
		return this.brandService.getAll();
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}
	
	@PutMapping("update")
	public Result add(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}
	
	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.brandService.delete(id);
	}
	
}
