package com.btkAkademi.rentACar.business.requests.carMaintenanceRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {

	private int id;
	private LocalDate maintenanceStartDate;
	private LocalDate maintenanceFinishDate;
	private int carId;
}
