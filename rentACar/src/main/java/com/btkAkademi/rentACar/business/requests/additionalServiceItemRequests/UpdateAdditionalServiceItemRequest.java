package com.btkAkademi.rentACar.business.requests.additionalServiceItemRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceItemRequest {
	private int id;
	private String name;
	private double price;

}
