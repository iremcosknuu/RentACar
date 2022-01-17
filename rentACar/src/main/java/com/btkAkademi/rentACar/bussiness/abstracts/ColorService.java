package com.btkAkademi.rentACar.bussiness.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.bussiness.dtos.ColorListDto;
import com.btkAkademi.rentACar.bussiness.requests.colorRequests.CreateColorRequest;
import com.btkAkademi.rentACar.bussiness.requests.colorRequests.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface ColorService {
	DataResult<List<ColorListDto>> getAll();
	
    Result add(CreateColorRequest createColorRequest);
	Result update(UpdateColorRequest updateColorRequest);
	Result delete(int id);
}
