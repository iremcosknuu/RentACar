package com.btkAkademi.rentACar.business.requests.fuelTypeRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFuelTypeRequest {
	private int id;
	private String name;

}
