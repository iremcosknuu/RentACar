package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceIndividualCustomerDto {
	private int id;
	private LocalDate invoicedDate;
	private int rentalId;
	private LocalDate rentedDate;
	private LocalDate returnedDate;
	private double totalPrice;
	private String nationalityId;
	private String firstName;
	private String lastName;
	private String email;
	
}
