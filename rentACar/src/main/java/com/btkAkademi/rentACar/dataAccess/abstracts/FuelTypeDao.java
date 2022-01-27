package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.FuelType;

public interface FuelTypeDao extends JpaRepository<FuelType, Integer> {

}
