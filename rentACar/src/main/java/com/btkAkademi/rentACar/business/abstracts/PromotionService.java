package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.PromotionListDto;
import com.btkAkademi.rentACar.business.requests.promotionRequests.CreatePromotionRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PromotionService {
	DataResult<List<PromotionListDto>> getAll();
	DataResult<PromotionListDto> findById(int id);
	
	Result add(CreatePromotionRequest createPromotionRequest);

}
