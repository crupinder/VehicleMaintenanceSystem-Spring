package com.rupinder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="service_center")
public class ServiceCenter {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int code;
	private String address;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="serviceCenter", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Employee> employeeList;
	
	public ServiceCenter() {
		super();
		employeeList = new ArrayList<Employee>();
	}
	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
