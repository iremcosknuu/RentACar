package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Integer> {
	IndividualCustomer findByEmail(String email);
}
