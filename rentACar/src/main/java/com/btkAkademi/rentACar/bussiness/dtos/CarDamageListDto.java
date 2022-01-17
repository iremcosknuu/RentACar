package com.btkAkademi.rentACar.bussiness.dtos;

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
