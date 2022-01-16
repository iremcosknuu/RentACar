package com.btkAkademi.rentACar.bussienes.requests.additionalServiceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddionalServiceRequest {
	private int id;
	private String name;
	private double price;
	private int rentalId;

}
