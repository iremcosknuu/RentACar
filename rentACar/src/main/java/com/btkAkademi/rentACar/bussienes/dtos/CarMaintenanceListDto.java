package com.btkAkademi.rentACar.bussienes.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListDto {
	private int id;
	private LocalDate maintenanceStartDate;
	private LocalDate maintenanceFinishDate;
	private int carId;
}
