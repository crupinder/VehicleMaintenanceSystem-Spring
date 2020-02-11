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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupinder.dao.MaintenanceRequestRepo;
import com.rupinder.dao.ServiceCenterRepo;
import com.rupinder.model.MaintenanceRequest;
import com.rupinder.model.RequestBodyMaintenanceRequest;
import com.rupinder.model.ServiceCenter;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin/manage_service_centers")
public class AdminServiceCenterController {
	@Autowired
	private ServiceCenterRepo serviceCenterRepo;
	
	@Autowired
	private MaintenanceRequestRepo maintenanceRequestRepo;
	
	@PostMapping("/send_maintenance_request")
	public MaintenanceRequest sendMaintenanceRequest(@RequestBody RequestBodyMaintenanceRequest requestBodyMaintenanceRequest) {
		MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
		maintenanceRequest.setVehicleVin(requestBodyMaintenanceRequest.getVehicleVin());
		maintenanceRequest.setServiceCenterCode(requestBodyMaintenanceRequest.getServiceCenterCode());
		maintenanceRequest.setServiceType(requestBodyMaintenanceRequest.getServiceType());
		maintenanceRequest.setAssigned(false);
		maintenanceRequest.setIsCompleted(false);
		
		maintenanceRequestRepo.save(maintenanceRequest);
		return maintenanceRequest;
	}
	
	@PostMapping("/service_centers")
	public ServiceCenter createServiceCenter(@RequestBody ServiceCenter serviceCenter) {
		return serviceCenterRepo.save(serviceCenter);
	}
	
	@GetMapping("/service_centers/{sid}")
	public ServiceCenter getServiceCenterById(@PathVariable("sid") int id) {
		return serviceCenterRepo.findById(id).get();
	}
	
	@GetMapping("/service_centers")
	public List<ServiceCenter> getAllServiceCenters(){
		List<ServiceCenter> serviceCenters = new ArrayList<>();
		serviceCenterRepo.findAll().forEach(serviceCenters::add);   
		return serviceCenters;
	}
	
	@PutMapping(path = "/service_centers/{id}")
	public ResponseEntity<ServiceCenter> updateServiceCenter(@PathVariable("id") int id, @RequestBody ServiceCenter serviceCenter) {
		Optional<ServiceCenter> serviceCenterData = serviceCenterRepo.findById(id);
		if(serviceCenterData.isPresent()) {
			ServiceCenter s = serviceCenterData.get();
			s.setAddress(serviceCenter.getAddress());
			return new ResponseEntity<>(serviceCenterRepo.save(s), HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/service_centers/{sid}")
	public ResponseEntity<String> deleteServiceCenterById(@PathVariable("sid") int id) {
		serviceCenterRepo.deleteById(id);
		return new ResponseEntity<>("Service Center has been deleted ", HttpStatus.OK);
	}
	
	@DeleteMapping("/service_centers")
	public ResponseEntity<String> deleteAllServiceCenters(){
		serviceCenterRepo.deleteAll();
		return new ResponseEntity<>("All Service Centers have been deleted ", HttpStatus.OK);
	}
}
