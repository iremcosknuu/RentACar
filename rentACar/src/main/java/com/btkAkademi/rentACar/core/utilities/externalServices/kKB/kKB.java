package com.btkAkademi.rentACar.core.utilities.externalServices.kKB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class kKB {

	public int CorporteCustomerFindexScore(String NationalityId) {
		int max= 1900;
		int min= 650;
				
		int score = (int) Math.floor(Math.random()*(max-min+1)+min);
		return score;
	}
	
	public int IndividuaCustomerFindexScore(String taxNumber) {
		int max= 1900;
		int min= 650;
				
		int score = (int) Math.floor(Math.random()*(max-min+1)+min);
		return score;
	}
}
