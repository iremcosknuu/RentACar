package com.btkAkademi.rentACar.bussienes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageListDto {
	private int id;
	private String description;
	private int carId;

}
