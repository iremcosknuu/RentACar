package com.btkAkademi.rentACar.core.utilities.adapters.bank.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface BankAdapterService {
	Result checkIfLimitEnough(String cardNo,String expirationDate,String cVV,double amount);
}
