package com.btkAkademi.rentACar.bussienes.requests.userRequests.IndividualCustomerRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String email;
	private String password;
}
