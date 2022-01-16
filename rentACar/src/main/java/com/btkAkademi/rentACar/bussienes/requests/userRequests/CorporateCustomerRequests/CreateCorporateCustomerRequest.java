package com.btkAkademi.rentACar.bussienes.requests.userRequests.CorporateCustomerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest{

	private String companyName;
	private String taxNumber;
	private String email;
	private String password;
	
}
