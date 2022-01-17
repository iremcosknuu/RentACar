package com.btkAkademi.rentACar.bussiness.requests.carMaintenanceRequests;

import java.time.LocalDate;

import com.btkAkademi.rentACar.entities.concretes.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {
	private LocalDate maintenanceStartDate;
	private LocalDate maintenanceFinishDate;
	private int carId;
}
