package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;

public interface CarMaintenanceDao extends JpaRepository<CarMaintenance, Integer>{
	CarMaintenance findByCarIdAndMaintenanceFinishDateIsNull(int carId);
}
