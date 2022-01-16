package com.btkAkademi.rentACar.bussienes.requests.paymentRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {
	private int id;
	private LocalDate paymentTime;
	private double totalPaymentAmount;
	private int rentalId;

}
