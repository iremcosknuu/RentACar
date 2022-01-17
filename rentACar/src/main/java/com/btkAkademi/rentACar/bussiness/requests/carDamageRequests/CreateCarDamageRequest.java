package com.btkAkademi.rentACar.bussiness.requests.carDamageRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateCarDamageRequest {
	private int carId ;
	private String description;	


}