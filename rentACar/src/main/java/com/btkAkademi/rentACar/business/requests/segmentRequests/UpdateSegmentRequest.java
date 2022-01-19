package com.btkAkademi.rentACar.business.requests.segmentRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSegmentRequest {
	private int id;
	private String name;
}
