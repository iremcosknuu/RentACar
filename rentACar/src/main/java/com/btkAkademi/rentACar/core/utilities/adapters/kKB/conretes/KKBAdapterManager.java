package com.btkAkademi.rentACar.core.utilities.adapters.kKB.conretes;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.core.utilities.adapters.kKB.abstracts.FindexAdaperService;
import com.btkAkademi.rentACar.core.utilities.externalServices.kKB.kKB;

@Service
public class KKBAdapterManager implements FindexAdaperService {

	@Override
	public int checkIfKkbLimitIndividualCustomer(String nationalityId) {
		kKB kkb = new kKB();
		return kkb.IndividuaCustomerFindexScore(nationalityId);
	}

	@Override
	public int checkIfKkbLimitCorporateCustomer(String taxNumber) {
		kKB kKB = new kKB();
		return kKB.CorporteCustomerFindexScore(taxNumber);
	}

}
