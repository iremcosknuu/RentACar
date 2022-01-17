package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.PromotionService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.PromotionListDto;
import com.btkAkademi.rentACar.business.requests.promotionRequests.CreatePromotionRequest;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PromotionDao;
import com.btkAkademi.rentACar.entities.concretes.Promotion;

@Service
public class PromotionManager implements PromotionService {

	private PromotionDao promotionDao;
	private ModelMapperService modelMapperService;
	
	public PromotionManager(PromotionDao promotionDao, ModelMapperService modelMapperService) {
		super();
		this.promotionDao = promotionDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<PromotionListDto>> getAll() {
		List<Promotion> promotionList = this.promotionDao.findAll();
		List<PromotionListDto> response = promotionList.stream()
				.map(promotion -> modelMapperService.forDto()
				.map(promotionList, PromotionListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<PromotionListDto>>(response);
	}
	
	@Override
	public DataResult<PromotionListDto> findById(int id) {
		Promotion promotion = this.promotionDao.getById(id);
		PromotionListDto response = modelMapperService.forDto().map(promotion, PromotionListDto.class);
		return new SuccessDataResult<PromotionListDto>(response);
	}

	@Override
	public Result add(CreatePromotionRequest createPromotionRequest) {
		Result result = businessRules.run(
				chechIfPromotionCodeAlreadyExist(createPromotionRequest.getPromotionCode())
				);
		
		if(result != null) {
			return result;
		}
		
		Promotion promotion = modelMapperService.forRequest().map(createPromotionRequest, Promotion.class);
		this.promotionDao.save(promotion);
		
		return new SuccessResult(Messages.promotionAdded);
	}
	
	public Result chechIfPromotionCodeAlreadyExist(String promotionCode) {
		if(this.promotionDao.findByPromotionCode(promotionCode) != null) {
			return new ErrorResult(Messages.promorionCodeAlreadyExists);
		}
		return new SuccessResult();
	}


}
