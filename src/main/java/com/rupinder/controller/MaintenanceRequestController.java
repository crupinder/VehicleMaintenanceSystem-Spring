package com.rupinder.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupinder.dao.EmployeeRepo;
import com.rupinder.dao.MaintenanceRequestRepo;
import com.rupinder.dao.ServiceCenterRepo;
import com.rupinder.dao.VehicleRepo;
import com.rupinder.model.Customer;
import com.rupinder.model.Employee;
import com.rupinder.model.MaintenanceRequest;
import com.rupinder.model.ServiceCenter;
import com.rupinder.model.Vehicle;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/maintenance_request")
public class MaintenanceRequestController {
	
	@Autowired
	MaintenanceRequestRepo maintenanceRequestRepo;
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	VehicleRepo vehicleRepo;
	
	@Autowired
	ServiceCenterRepo serviceCenterRepo;
	
	@Autowired
	private JavaMailSender sender;
	
	@PostMapping("/create_request")
	public MaintenanceRequest createRequest(@RequestBody MaintenanceRequest maintenanceRequest) {
		return maintenanceRequestRepo.save(maintenanceRequest);
	}

	@GetMapping("/not_assigned")
	public List<MaintenanceRequest> getMaintenanceRequestsNotAssigned(){
		return maintenanceRequestRepo.getMaintenanceRequestsNotAssigned();
	}
	
	@GetMapping("/get_request/{id}")
	public MaintenanceRequest getRequest(@PathVariable("id") int id) {
		return maintenanceRequestRepo.findById(id).get();
	}
	
	@PutMapping("/assign_task_to_employee/{eid}")
	public ResponseEntity<MaintenanceRequest> assignTo(@PathVariable("eid") int id, @RequestBody MaintenanceRequest maintenanceRequest){
		Optional<Employee> employeeData = employeeRepo.findById(id);
		if(employeeData.isPresent()) {
			Employee employee = employeeData.get();
			maintenanceRequest.setEmployee(employee);
			maintenanceRequest.setAssigned(true);
			employee.getMaintenanceRequest().add(maintenanceRequest);
			employeeRepo.save(employee);
			return new ResponseEntity<>(maintenanceRequestRepo.save(maintenanceRequest), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Used to mark as completed
	@PutMapping("/update_request/{id}")
	public ResponseEntity<MaintenanceRequest> updateRequest(@PathVariable("id") int id, @RequestBody MaintenanceRequest maintenanceRequest) {
		Optional<MaintenanceRequest> requestData = maintenanceRequestRepo.findById(id);
		if(requestData.isPresent()) {
			MaintenanceRequest request = requestData.get();
			request.setAssigned(maintenanceRequest.isAssigned());
			request.setIsCompleted(maintenanceRequest.getIsCompleted());
			request.setEmployee(maintenanceRequest.getEmployee());
			request.setServiceCenterCode(maintenanceRequest.getServiceCenterCode());
			request.setServiceType(maintenanceRequest.getServiceType());
			request.setVehicleVin(maintenanceRequest.getVehicleVin());
			return new ResponseEntity<>(maintenanceRequestRepo.save(request), HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/get_pending_complete")
	public List<MaintenanceRequest> getPendingCompleteRequests(){
		return maintenanceRequestRepo.findAll();
	}
	
	@GetMapping("/get_employee_assigned_tasks/{employeeId")
	public List<MaintenanceRequest> getEmployeeAssignedTasks(@PathVariable("employeeId") int id){
		return maintenanceRequestRepo.getEmployeeAssignedTasks(id);
	}
	
	@GetMapping("/get_completed_tasks/{employeeId}")
	public List<MaintenanceRequest> getCompletedTasks(@PathVariable("employeeId") int id){
		return maintenanceRequestRepo.getCompletedTasks(id);
	}
	
	@GetMapping("/mark_as_completed/{id}")
	public MaintenanceRequest markAsCompleted(@PathVariable("id") int id) {
		MaintenanceRequest m = maintenanceRequestRepo.findById(id).get();
		SimpleMailMessage msg = new SimpleMailMessage();
		String vin = m.getVehicleVin();
		Vehicle v = vehicleRepo.findByVin(vin);
		Customer c = v.getCustomer();
		m.setIsCompleted(true);
		msg.setTo(c.getEmail());
		msg.setSubject("Email from VMS");
		msg.setText("Your request has been successfully processed");
		sender.send(msg);
		return maintenanceRequestRepo.save(m);
	}
	
	@GetMapping("/get_employee_list_by_service_center_code/{code}")
	public List<Employee> getEmployeeListByServiceCenterCode(@PathVariable("code") int code){
		ServiceCenter serviceCenter = serviceCenterRepo.findByCode(code);
		return serviceCenter.getEmployeeList();
	}
	
	@GetMapping("/get_vehicles_not_assigned")
	public List<Vehicle> getVehiclesNotAssigned(){
		List<Vehicle> vehicles = vehicleRepo.findAll();
		List<String> vins = maintenanceRequestRepo.getVinsFromMaintenanceRequest();
		for (String vin : vins) {
			Vehicle vehicle = vehicleRepo.findByVin(vin);
			if(vehicles.contains(vehicle))
				vehicles.remove(vehicle);
		}
		return vehicles;
	}
	
	@DeleteMapping("/delete_request/{id}")
	public ResponseEntity<String> deleteRequestById(@PathVariable("id") int id) {
		maintenanceRequestRepo.deleteById(id);
		return new ResponseEntity<>("Request has been deleted", HttpStatus.OK);
	}
}
