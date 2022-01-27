package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.UpdateAddionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService{
	
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;

	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService,
			@Lazy RentalService rentalService) {
		super();
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}

	@Override
	public DataResult<List<AdditionalServiceListDto>> findAllByRentalId(int rentalId) {
		List<AdditionalService> additionalServiceList = this.additionalServiceDao.findAllByRentalId(rentalId);
		List<AdditionalServiceListDto> response = additionalServiceList.stream().map(
				additionalService -> modelMapperService.forDto().map(additionalService, AdditionalServiceListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<AdditionalServiceListDto>>(response);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		Result result = businessRules.run(checkIfRentalExists(createAdditionalServiceRequest.getRentalId()));
		if (result != null) {
			return result;
		}

		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest,
				AdditionalService.class);
		// avoid error
		additionalService.setId(0);
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.additionalServiceAdded);
	}

	@Override
	public Result addAll(List<CreateAdditionalServiceRequest> createAdditionalServiceRequests) {
		Result result = businessRules.run(checkIfRentalExists(createAdditionalServiceRequests.get(0).getRentalId()));
		if (result != null) {
			return result;
		}

		List<AdditionalService> response = createAdditionalServiceRequests.stream()
				.map(service -> modelMapperService.forRequest().map(service, AdditionalService.class))
				.collect(Collectors.toList());
		for (AdditionalService service : response) {
			service.setId(0);
		}
		this.additionalServiceDao.saveAll(response);
		return new SuccessResult(Messages.additionalServiceAdded);
	}

	@Override
	public Result update(UpdateAddionalServiceRequest updateAdditionalServiceRequest) {
		Result result = businessRules.run(checkIfRentalExists(updateAdditionalServiceRequest.getRentalId()));

		if (result != null) {
			return result;
		}
		
		AdditionalService additionalService = modelMapperService.forRequest().map(updateAdditionalServiceRequest,
				AdditionalService.class);
		additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.additionalServiceUpdated);
	}

	@Override
	public Result delete(int id) {
		if (additionalServiceDao.existsById(id)) {
			additionalServiceDao.deleteById(id);
			return new SuccessResult(Messages.additionalServiceDeleted);
		} else
			return new ErrorResult(Messages.additionalServiceNotFound);
	}

	private Result checkIfRentalExists(int rentalId) {
		if (!rentalService.findById(rentalId).isSuccess()) {
			return new ErrorResult(Messages.rentalIdNotExists);
		} else
			return new SuccessResult();
	}
	

}
