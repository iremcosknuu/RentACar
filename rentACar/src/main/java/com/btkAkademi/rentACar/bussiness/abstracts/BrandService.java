package com.btkAkademi.rentACar.bussiness.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.bussiness.dtos.BrandListDto;
import com.btkAkademi.rentACar.bussiness.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.bussiness.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface BrandService {
    DataResult<List<BrandListDto>> getAll();
    
	Result add(CreateBrandRequest createBrandRequest);
	Result update(UpdateBrandRequest updateBrandRequest);
	Result delete(int id);
}