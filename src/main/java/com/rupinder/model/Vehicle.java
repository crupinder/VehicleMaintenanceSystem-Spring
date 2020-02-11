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
@Table(name="vehicle")
public class Vehicle {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String vin;
	private String warrantyPeriod;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	public Vehicle() {
		super();
	}
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getWarrantyPeriod() {
		return warrantyPeriod;
	}
	public void setWarrantyPeriod(String warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
