package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionListDto {

	private String name;
	private String promotionCode;
	private double discountRate;
	private LocalDate promotionStartDate;
	private LocalDate promotionEndDate;
	
}
