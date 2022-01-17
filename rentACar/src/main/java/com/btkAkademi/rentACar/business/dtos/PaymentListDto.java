package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentListDto {
	private int id;
	private double dailyPrice;
	private double additionalServicePrice;
	private double totalAmount;
	private int rental_Id;
}
