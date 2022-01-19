package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.SegmentService;
import com.btkAkademi.rentACar.business.dtos.SegmentListDto;
import com.btkAkademi.rentACar.business.requests.segmentRequests.CreateSegmentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@CrossOrigin
@RestController
@RequestMapping("/api/segments")
public class SegmentsController {

	private SegmentService segmentService;
	
	@Autowired
	public SegmentsController(SegmentService segmentService) {
		super();
		this.segmentService = segmentService;
	}

	@GetMapping("getall")
	public DataResult<List<SegmentListDto>> getAll(){
		 return this.segmentService.getAll();
	}
	
	@GetMapping("findyid/{id}")
	public DataResult<SegmentListDto> findById(@PathVariable int id){
		return this.segmentService.findById(id);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateSegmentRequest createSegmentRequest) {
		return this.segmentService.add(createSegmentRequest);
	}
	
}
