package com.btkAkademi.rentACar.bussiness.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerListDto{
	private String companyName;
	private String taxnumber;
}
