package com.btkAkademi.rentACar.core.utilities.adapters.kKB.abstracts;

public interface FindexAdaperService {
	public int checkIfKkbLimitIndividualCustomer (String nationalityId);
	public int checkIfKkbLimitCorporateCustomer(String taxNumber);
}
