package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.PromotionService;
import com.btkAkademi.rentACar.business.dtos.PromotionListDto;
import com.btkAkademi.rentACar.business.requests.promotionRequests.CreatePromotionRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@CrossOrigin
@RestController
@RequestMapping("/api/promotions")
public class PromotionsController {
	
	private PromotionService promotionService;
	
	public PromotionsController(PromotionService promotionService) {
		super();
		this.promotionService = promotionService;
	}
	
	@GetMapping("getall")
	public DataResult<List<PromotionListDto>> getAll(){
		return this.promotionService.getAll();
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreatePromotionRequest createPromotionRequest) {
		return this.promotionService.add(createPromotionRequest);
	}
	

}
