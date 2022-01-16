package com.btkAkademi.rentACar.bussienes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceListDto {
	
	private String name;
	private double price;
	private int rentalId;
}
