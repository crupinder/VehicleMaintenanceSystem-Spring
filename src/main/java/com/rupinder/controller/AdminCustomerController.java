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

import com.rupinder.dao.CustomerRepo;
import com.rupinder.dao.EmployeeRepo;
import com.rupinder.dao.MaintenanceRequestRepo;
import com.rupinder.dao.ServiceCenterRepo;
import com.rupinder.dao.TransactionRepo;
import com.rupinder.dao.VehicleRepo;
import com.rupinder.model.Customer;
import com.rupinder.model.CustomerVehicle;
import com.rupinder.model.Employee;
import com.rupinder.model.MaintenanceRequest;
import com.rupinder.model.ServiceCenter;
import com.rupinder.model.Transaction;
import com.rupinder.model.Vehicle;

@RestController 
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin/manage_customers")
public class AdminCustomerController {
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	ServiceCenterRepo serviceCenterRepo;
	
	@Autowired
	VehicleRepo vehicleRepo;
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	MaintenanceRequestRepo maintenanceRequestRepo;
	
	@Autowired
	TransactionRepo transactionRepo;
	
	@PostMapping("/customers")
	public Customer createCustomer(@RequestBody Customer customer) {	
		/*Customer customer1 = new Customer();
		Vehicle v1 = new Vehicle();
		Transaction transaction1 = new Transaction();
		MaintenanceRequest mr1 = new MaintenanceRequest();
		MaintenanceRequest mr2 = new MaintenanceRequest();
		MaintenanceRequest mr3 = new MaintenanceRequest();
		Employee e1 = new Employee();
		
		mr1.setAssigned(false);
		mr1.setCompleted(false);
		mr1.setEmployee(e1);
		mr1.setServiceCenterCode(1001);
		mr1.setServiceType("Oil Change");
		mr1.setVehicleVin("11");
		
		mr2.setAssigned(true);
		mr2.setCompleted(false);
		mr2.setEmployee(e1);
		mr2.setServiceCenterCode(1002);
		mr2.setServiceType("Battery Change");
		mr2.setVehicleVin("12");
		
		mr3.setAssigned(false);
		mr3.setCompleted(false);
		mr3.setEmployee(e1);
		mr3.setServiceCenterCode(1002);
		mr3.setServiceType("Battery Change");
		mr3.setVehicleVin("12");
		
		transaction1.setEntity(22.22);
		transaction1.setEntityName("Oil Change");
		customer1.setName("Alfredo");
		transaction1.setCustomer(customer1);
		customer1.getTransactions().add(transaction1);
		customer1.setEmail("alfredo@gmail.com");
		customer1.setAddress("1124 Highfield Ct");
		v1.setVin("11"); 
		v1.setCustomer(customer1); 
		v1.setWarrantyPeriod("1-2-2020");
		 
		Customer customer2 = new Customer(); 
		Vehicle v2 = new Vehicle(); 
		Transaction transaction2 = new Transaction();
		Employee e2 = new Employee();
		customer2.setName("Dian");
		transaction2.setCustomer(customer2);
		transaction2.setEntity(33.33);
		transaction2.setEntityName("Engine Repair");
		customer2.getTransactions().add(transaction2);
		customer2.setEmail("dian@gmail.com");
		customer2.setAddress("1126 Highfield Ct");
		v2.setVin("12"); 
		v2.setCustomer(customer2);
		v2.setWarrantyPeriod("1-2-2021"); 
		
		customer1.getVehicles().add(v1);
		customer2.getVehicles().add(v2);
		
		ServiceCenter sc1 = new ServiceCenter();
		sc1.setAddress("abc");
		sc1.setCode(1001);
		
		ServiceCenter sc2 = new ServiceCenter();
		sc2.setAddress("def");
		sc2.setCode(1002);
		

		e1.getMaintenanceRequest().add(mr1);
		e1.setName("Igor");
		e1.setServiceCenter(sc1);
		
		e2.getMaintenanceRequest().add(mr3);
		e2.setName("Ben");
		e2.setServiceCenter(sc2);
		
		sc1.getEmployeeList().add(e1);
		sc2.getEmployeeList().add(e2);

		serviceCenterRepo.save(sc1);
		serviceCenterRepo.save(sc2);
		
		customerRepo.save(customer1);
		customerRepo.save(customer2);
		
		vehicleRepo.save(v1);
		vehicleRepo.save(v2);

		transactionRepo.save(transaction1);
		transactionRepo.save(transaction2);
		
		maintenanceRequestRepo.save(mr1);
		maintenanceRequestRepo.save(mr2);
		maintenanceRequestRepo.save(mr3);
		
		employeeRepo.save(e1);
		employeeRepo.save(e2);
		
		return customer2; */
		
		return customerRepo.save(customer);
	}
	
	@GetMapping("/customers/{id}") 
	public Customer getCustomer(@PathVariable("id") int id) {
		Optional<Customer> customer = customerRepo.findById(id);
		return customer.get(); 
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		List<Customer> customers=new ArrayList<>();
		customerRepo.findAll().forEach(customers::add); //  Similar to
														//	List<Customer>list = customerRepo.findAll();
														//	for(Customer cust:list) {
														//	customers.add(cust);
														//	}
		
		return customers;
	}
	 
	@PutMapping(path = "/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
		Optional<Customer> customerData = customerRepo.findById(id);
		if(customerData.isPresent()) {
			Customer cust = customerData.get();
			cust.setName(customer.getName());
			cust.setEmail(customer.getEmail());
			cust.setAddress(customer.getAddress());
			cust.setTransactions(customer.getTransactions());
			cust.setVehicles(customer.getVehicles());
			return new ResponseEntity<>(customerRepo.save(cust), HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("id") int id) {
		customerRepo.deleteById(id);
		return new ResponseEntity<>("Customer has been deleted", HttpStatus.OK);
	}
	
	@DeleteMapping("/customers")
	public ResponseEntity<String> deleteAllCustomers(){
		customerRepo.deleteAll();
		return new ResponseEntity<>("All Customers have been deleted ",HttpStatus.OK);
	}
	
	@PostMapping("/vehicles/{custId}")
	public ResponseEntity<Vehicle> addVehicle(@RequestBody CustomerVehicle custVehicle, @PathVariable("custId") int id) {
		Optional<Customer> customerData = customerRepo.findById(id);
		Vehicle vehicle = new Vehicle();
		if(customerData.isPresent()) {
			Customer customer = customerData.get();
			customer.setName(custVehicle.getName());
			customer.setAddress(custVehicle.getAddress());
			customer.setEmail(custVehicle.getEmail());
			if(!custVehicle.getVin().equals("") && !custVehicle.getWarrantyPeriod().equals("")) {
				vehicle.setVin(custVehicle.getVin());
				vehicle.setWarrantyPeriod(custVehicle.getWarrantyPeriod());
				vehicle.setCustomer(customer);
				customer.getVehicles().add(vehicle);
				vehicleRepo.save(vehicle);
			}
			customerRepo.save(customer);
			return new ResponseEntity<>(vehicle, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/get_all_transactions/{tid}")
	public List<Transaction> getAllTransactions(@PathVariable("tid") Integer id){
		return transactionRepo.getAllTransactions(id);
	}
}
