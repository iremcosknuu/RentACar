package com.btkAkademi.rentACar.bussiness.requests.userRequests.CorporateCustomerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	private int id;
	private String companyName;
	private String taxNumber;
	private String email;
	private String password;
}
