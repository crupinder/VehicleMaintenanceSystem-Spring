package com.rupinder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="maintenance_request")
public class MaintenanceRequest {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String serviceType;
	private String vehicleVin;
	private int serviceCenterCode;
	private boolean isCompleted = false;
	private boolean isAssigned = false;
	
	@ManyToOne
	@JoinColumn(name="employee_id")  //means that our MaintenanceRequest entity will have a foreign key column named
	private Employee employee;			//employee_id referring to the primary attribute id of our Employee entity.
	
	public MaintenanceRequest(String serviceType, String vehicleVin, int serviceCenterCode, boolean isCompleted,
			boolean isAssigned, Employee employee) {
		super();
		this.serviceType = serviceType;
		this.vehicleVin = vehicleVin;
		this.serviceCenterCode = serviceCenterCode;
		this.isCompleted = isCompleted;
		this.isAssigned = isAssigned;
		this.employee = employee;
	}
	public MaintenanceRequest() {
		super();
	}
	public boolean isAssigned() {
		return isAssigned;
	}
	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}
	public Employee getEmployee() {
		return employee;
	}
	public String getVehicleVin() {
		return vehicleVin;
	}
	public void setVehicleVin(String vehicleVin) {
		this.vehicleVin = vehicleVin;
	}
	public int getServiceCenterCode() {
		return serviceCenterCode;
	}
	public void setServiceCenterCode(int serviceCenterCode) {
		this.serviceCenterCode = serviceCenterCode;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public boolean getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
}
