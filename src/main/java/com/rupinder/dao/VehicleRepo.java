package com.rupinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupinder.model.Vehicle;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {
	Vehicle findByVin(String vin);
}
