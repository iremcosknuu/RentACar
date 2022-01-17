package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCorporateCustomerDto {

	private int id;
	private LocalDate invoicedDate;
	private int rentalId;
	private LocalDate rentedDate;
	private LocalDate returnedDate;
	private double totalPrice;
	private String companyName;
	private String taxNumber;
	private String emaiil;
	private List<AdditionalServiceListDto> additionalServiceList;
	
}
