package com.btkAkademi.rentACar.bussienes.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.bussienes.abstracts.CityService;
import com.btkAkademi.rentACar.bussienes.constants.Messages;
import com.btkAkademi.rentACar.bussienes.dtos.CarListDto;
import com.btkAkademi.rentACar.bussienes.dtos.CityListDto;
import com.btkAkademi.rentACar.bussienes.requests.cityRequests.CreateCityRequest;
import com.btkAkademi.rentACar.bussienes.requests.cityRequests.UpdateCityRequest;
import com.btkAkademi.rentACar.core.utilities.business.businessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CityDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.City;

@Service
public class CityManager implements CityService{

	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
		super();
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CityListDto>> getAll() {
		List<City> cityList = this.cityDao.findAll();
		List<CityListDto> response= cityList.stream()
				.map(city -> modelMapperService.forDto()
				.map(city, CityListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CityListDto>>(response);
	}

	@Override
	public Result add(CreateCityRequest createCityRequest) {
		Result result = businessRules.run(
				checkIfCityNameExists(createCityRequest.getName())
				);
		if (result != null) {
			return result;
		}
		
		City city= modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityDao.save(city);
		return new SuccessResult(Messages.cityAdded);
	}
	
	@Override
	public Result update(UpdateCityRequest updateCityRequest) {
		Result result = businessRules.run(
				checkIfCityNameExists(updateCityRequest.getName()),
				checkIfCityIdExists(updateCityRequest.getId())
				);
		if (result != null) {
			return result;
		}
		
		City cityToUpdate = this.cityDao.getById(updateCityRequest.getId());
		
		cityToUpdate = modelMapperService.forRequest().map(updateCityRequest, City.class);
		this.cityDao.save(cityToUpdate);
		return new SuccessResult(Messages.cityUpdated);
	}

	@Override
	public Result delete(int id) {
		Result result = businessRules.run(
				checkIfCityIdExists(id)
				);
		if (result != null) {
			return result;
		}
		
		this.cityDao.deleteById(id);
		return new SuccessResult(Messages.cityDeleted);
	}


	@Override
	public DataResult<CityListDto> findCityById(int id) {
		if(!this.cityDao.existsById(id)) {
			return new ErrorDataResult<CityListDto>(Messages.cityNotFound);
		}
		CityListDto cityList = modelMapperService.forDto().map(cityDao.findById(id).get(), CityListDto.class);		
		return new SuccessDataResult<CityListDto>(cityList);
	}

	public Result checkIfCityNameExists(String cityName) {
		if(this.cityDao.findByName(cityName)!= null) {
			return new ErrorResult(Messages.cityNameExists);
		}
		return new SuccessResult();
	}
	
	public Result checkIfCityIdExists(int id) {
		if(!this.cityDao.existsById(id)) {
			return new ErrorResult(Messages.cityIdNotExist);
		}
		return new SuccessResult();
	}

}
