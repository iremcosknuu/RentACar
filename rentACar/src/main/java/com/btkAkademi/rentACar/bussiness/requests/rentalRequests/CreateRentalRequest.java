package com.btkAkademi.rentACar.bussiness.requests.rentalRequests;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	private LocalDate rentDate;
	private LocalDate returnDate;
	private int rentedKilometer;
	private int returnedKilometer;
	private int pickUpCityId;
	private int returnCityId;
	private int customerId;	
	private int carId;
}