package com.rupinder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String address;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="customer", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Vehicle> vehicles;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="customer", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Transaction> transactions;
	
	public Customer() {
		super();
		vehicles = new ArrayList<>();
		transactions = new ArrayList<>();
	}
	public Customer(String name, String email, String address) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		vehicles = new ArrayList<>();
		transactions = new ArrayList<>();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}
