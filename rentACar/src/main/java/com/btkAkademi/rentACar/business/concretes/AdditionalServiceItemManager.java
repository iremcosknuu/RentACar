package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceItemService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceItemListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequests.CreateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequests.UpdateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceItemDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalServiceItem;

@Service
public class AdditionalServiceItemManager implements AdditionalServiceItemService {
	
	private ModelMapperService modelMapperService;
	private AdditionalServiceItemDao additionalServiceItemDao;

	// Dependency Injection
	@Autowired
	public AdditionalServiceItemManager(ModelMapperService modelMapperService,
			AdditionalServiceItemDao additionalServiceItemDao) {
		super();
		this.modelMapperService = modelMapperService;
		this.additionalServiceItemDao = additionalServiceItemDao;
	}

	@Override
	public Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {
		AdditionalServiceItem additionalServiceItem = this.modelMapperService.forRequest()
				.map(createAdditionalServiceItemRequest, AdditionalServiceItem.class);
		this.additionalServiceItemDao.save(additionalServiceItem);
		return new SuccessResult(Messages.additionalServiceAdded);
	}

	@Override
	public Result update(UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest) {
		AdditionalServiceItem additionalServiceItem = this.modelMapperService.forRequest()
				.map(updateAdditionalServiceItemRequest, AdditionalServiceItem.class);
		this.additionalServiceItemDao.save(additionalServiceItem);
		return new SuccessResult(Messages.additionalServiceUpdated);
	}

	@Override
	public Result delete(int id) {
		if (additionalServiceItemDao.existsById(id)) {
			additionalServiceItemDao.deleteById(id);
			return new SuccessResult(Messages.additionalServiceDeleted);
		} else
			return new ErrorResult();
	}

	@Override
	public DataResult<AdditionalServiceItemListDto> findById(int id) {
		if (additionalServiceItemDao.existsById(id)) {
			AdditionalServiceItem item = additionalServiceItemDao.findById(id).get();
			AdditionalServiceItemListDto response = modelMapperService.forDto().map(item,
					AdditionalServiceItemListDto.class);
			return new SuccessDataResult<AdditionalServiceItemListDto>();
		} else
			return new ErrorDataResult<AdditionalServiceItemListDto>(Messages.additionalServiceNotFound);
	}

	@Override
	public DataResult<List<AdditionalServiceItemListDto>> findAll() {
		List<AdditionalServiceItem> additionalServiceItems = this.additionalServiceItemDao.findAll();
		List<AdditionalServiceItemListDto> response = additionalServiceItems.stream()
				.map(additionalServiceItem -> modelMapperService.forDto().map(additionalServiceItem,
						AdditionalServiceItemListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(response);
	}

}
