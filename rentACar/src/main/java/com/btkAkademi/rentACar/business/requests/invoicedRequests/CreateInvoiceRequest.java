package com.btkAkademi.rentACar.business.requests.invoicedRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	private LocalDate invoicedDate;
	private int rentalId;
}
