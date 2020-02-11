package com.rupinder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupinder.dao.VehicleRepo;
import com.rupinder.model.Vehicle;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin/manage_vehicles")
public class AdminVehicleController {
	
	@Autowired
	VehicleRepo vehicleRepo;
	
	@GetMapping("/vehicles/{vid}")
	public Vehicle getVehicle(@PathVariable("vid") int id) {
		return vehicleRepo.findById(id).get();
	}
	
	@GetMapping("/vehicles")
	public List<Vehicle> getAllVehicles(){
		List<Vehicle> vehicles=new ArrayList<>();
		vehicleRepo.findAll().forEach(vehicles::add);   //  Similar to
														//	List<Vehicle>list = vehicleRepo.findAll();
														//	for(Vehicle v:list) {
														//	vehicles.add(v);
														//	}
		
		return vehicles;
	}
	
	@PutMapping(path = "/vehicles/{id}")
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") int id, @RequestBody Vehicle vehicle) {
		Optional<Vehicle> vehicleData = vehicleRepo.findById(id);
		if(vehicleData.isPresent()) {
			Vehicle v = vehicleData.get();
			v.setVin(vehicle.getVin());
			v.setWarrantyPeriod(vehicle.getWarrantyPeriod());
			v.setCustomer(vehicle.getCustomer());
			return new ResponseEntity<>(vehicleRepo.save(v), HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/vehicles/{vid}")
	public ResponseEntity<String> deleteVehicleById(@PathVariable("vid") int id) {
		vehicleRepo.deleteById(id);
		return new ResponseEntity<>("Vehicle has been deleted ", HttpStatus.OK);
	}
	
	@DeleteMapping("/vehicles")
	public ResponseEntity<String> deleteAllVehicles(){
		vehicleRepo.deleteAll();
		return new ResponseEntity<>("All vehicles have been deleted ", HttpStatus.OK);
	}
}
