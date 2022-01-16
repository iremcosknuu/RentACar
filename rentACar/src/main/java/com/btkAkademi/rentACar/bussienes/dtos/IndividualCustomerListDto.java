package com.btkAkademi.rentACar.bussienes.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerListDto{
	private String firstName;
	private String lastName;
	private LocalDate birthDate; 
}
