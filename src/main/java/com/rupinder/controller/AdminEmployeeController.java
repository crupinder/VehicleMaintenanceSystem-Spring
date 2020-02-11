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

import com.rupinder.dao.EmployeeRepo;
import com.rupinder.dao.ServiceCenterRepo;
import com.rupinder.model.Employee;
import com.rupinder.model.RequestBodyEmployee;
import com.rupinder.model.ServiceCenter;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin/manage_employees")
public class AdminEmployeeController {
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	ServiceCenterRepo serviceCenterRepo;
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody RequestBodyEmployee rbEmployee) {
		Employee employee = new Employee();
		employee.setName(rbEmployee.getName());
		ServiceCenter serviceCenter = serviceCenterRepo.findByCode(rbEmployee.getCode());
		serviceCenter.getEmployeeList().add(employee);
		employee.setServiceCenter(serviceCenter);
		employee = employeeRepo.save(employee);
		serviceCenterRepo.save(serviceCenter);
		return employee;
	}
	
	@GetMapping("/employees/{eid}")
	public Employee getEmployeeById(@PathVariable("eid") int id) {
		return employeeRepo.findById(id).get();
	}
	
	@GetMapping("/employees/get_employee_by_name/{name}")
	public Employee getEmployeeByName(@PathVariable("name") String name){
		return employeeRepo.findByName(name);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		List<Employee> employees = new ArrayList<>();
		employeeRepo.findAll().forEach(employees::add);   
		return employees;
	}
	
	@PutMapping(path = "/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody RequestBodyEmployee requestBodyEmployee) {
		Optional<Employee> employeeData = employeeRepo.findById(id);
		if(employeeData.isPresent()) {
			Employee employee = employeeData.get();
			employee.setName(requestBodyEmployee.getName());
			ServiceCenter serviceCenter = serviceCenterRepo.findByCode(requestBodyEmployee.getCode());
			if(employee.getServiceCenter().getCode() != serviceCenter.getCode()) {
				employee.getServiceCenter().getEmployeeList().remove(employee);
				employee.setServiceCenter(serviceCenter);
				serviceCenter.getEmployeeList().add(employee);
				serviceCenterRepo.save(serviceCenter);
			}
			return new ResponseEntity<>(employeeRepo.save(employee), HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/employees/{eid}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("eid") int id) {
		employeeRepo.deleteById(id);
		return new ResponseEntity<>("Employee has been deleted ", HttpStatus.OK);
	}
	
	@DeleteMapping("/employees")
	public ResponseEntity<String> deleteAllServiceCenters(){
		employeeRepo.deleteAll();
		return new ResponseEntity<>("All employees have been deleted ", HttpStatus.OK);
	}
}
