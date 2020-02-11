package com.rupinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.rupinder.dao.ServiceCenterRepo;
import com.rupinder.model.Employee;
import com.rupinder.model.ServiceCenter;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleMaintenanceSystem1Application {
	
	@Autowired
	private static ServiceCenterRepo serviceCenterRepo;

	public static void main(String[] args) {
		SpringApplication.run(VehicleMaintenanceSystem1Application.class, args);
		//setupEmployees();
		System.out.println("Inside main");
	}

	private static void setupEmployees() {
		ServiceCenter serviceCenter1 = new ServiceCenter();
		ServiceCenter serviceCenter2 = new ServiceCenter();
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		Employee e3 = new Employee();
		Employee e4 = new Employee();
		
		e1.setName("Employee 1");
		e2.setName("Employee 2");
		e3.setName("Employee 3");
		e4.setName("Employee 4");
		
		serviceCenter1.getEmployeeList().add(e1);
		serviceCenter1.getEmployeeList().add(e2);
		serviceCenter2.getEmployeeList().add(e3);
		serviceCenter2.getEmployeeList().add(e4);
		
		serviceCenterRepo.save(serviceCenter1);
		serviceCenterRepo.save(serviceCenter2);
	}
}
