package com.btkAkademi.rentACar.bussiness.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.bussiness.abstracts.ColorService;
import com.btkAkademi.rentACar.bussiness.constants.Messages;
import com.btkAkademi.rentACar.bussiness.dtos.ColorListDto;
import com.btkAkademi.rentACar.bussiness.requests.colorRequests.CreateColorRequest;
import com.btkAkademi.rentACar.bussiness.requests.colorRequests.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.ColorDao;
import com.btkAkademi.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ModelMapperService modelMapperService;

	private ColorDao colorDao;
	
	@Autowired
	public ColorManager(ModelMapperService modelMapperService, ColorDao colorDao) {
		this.modelMapperService = modelMapperService;
		this.colorDao = colorDao;
	}


	@Override
	public DataResult<List<ColorListDto>> getAll() {
		List<Color> colorList = this.colorDao.findAll();
		List<ColorListDto> response = colorList.stream()
				.map(color -> modelMapperService.forDto()
				.map(color,ColorListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ColorListDto>>(response);
	}


	@Override
	public Result add(CreateColorRequest createColorRequest) {
		Result result=businessRules.run(
				checkIfColorNameExists(createColorRequest.getName()));
		
		if(result!=null) {
			return result;
		}
		
		Color color= modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult(Messages.colorAdded);
	}
	
	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Result result=businessRules.run(
				checkIfColorIdExists(updateColorRequest.getId()));
		
		if(result!=null) {
			return result;
		}
		Color colorToUpdate = this.colorDao.getById(updateColorRequest.getId());
		colorToUpdate = modelMapperService.forRequest().map(updateColorRequest, Color.class);
		
		this.colorDao.save(colorToUpdate);
		
		return new SuccessResult(Messages.updateColor);
	}
	
	@Override
	public Result delete(int id) {
		Result result=businessRules.run(
				checkIfColorIdExists(id));
		
		if(result!=null) {
			return result;
		}
		
		this.colorDao.deleteById(id);
		return new SuccessResult(Messages.colorDeleted);
	}
	
	private Result checkIfColorNameExists(String colorName) {
		
		Color color = this.colorDao.findByName(colorName);
		if(color!=null) {
			return new ErrorResult(Messages.colorNameExists);
		}
		return new SuccessResult(Messages.getByIdColor);
	}

	public Result checkIfColorIdExists(int id) {
		if(!(this.colorDao.existsById(id))) {
			return new ErrorResult(Messages.colorNotFound);
		}
		return new SuccessResult();
	}


}
