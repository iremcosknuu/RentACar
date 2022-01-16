package com.btkAkademi.rentACar.bussienes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto {
	private int id;
	private double dailyPrice;	
	private int model;
	private String description;
	private int findexScore;	
	private int kilometer;	
	private String brandName;	
	private String colorName;
}
