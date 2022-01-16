package com.btkAkademi.rentACar.core.utilities.adapters.bank.concretes;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.bussienes.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.adapters.bank.abstracts.BankAdapterService;
import com.btkAkademi.rentACar.core.utilities.externalServices.bank.IsBank;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;

@Service
public class IsBankAdapterManager implements BankAdapterService {

	@Override
	public Result checkIfLimitEnough(String cardNo, String expirationDate, String cVV, double amount) {
		IsBank isBank = new IsBank();
		if( isBank.isLimitExists(cardNo,expirationDate,cVV,amount)) {
			return new SuccessResult();
		}else {	
			return new ErrorResult(Messages.limitNotEnough);
			}

	}

}
