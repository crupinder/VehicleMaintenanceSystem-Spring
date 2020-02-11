package com.rupinder.model;

 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="employee", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<MaintenanceRequest> maintenanceRequest;
	
	@ManyToOne
	@JoinColumn(name="service_center_id")
	private ServiceCenter serviceCenter;
	
	public Employee() {
		super();
		maintenanceRequest = new ArrayList<MaintenanceRequest>();
	}
	public ServiceCenter getServiceCenter() {
		return serviceCenter;
	}
	public void setServiceCenter(ServiceCenter serviceCenter) {
		this.serviceCenter = serviceCenter;
	}
	public List<MaintenanceRequest> getMaintenanceRequest() {
		return maintenanceRequest;
	}
	public void setMaintenanceRequest(List<MaintenanceRequest> maintenanceRequest) {
		this.maintenanceRequest = maintenanceRequest;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
