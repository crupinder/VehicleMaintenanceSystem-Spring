package com.rupinder.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment_transaction")
public class PaymentTransaction {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int adminAccountNumber;
	private int customerAccountNumber;
	private String date;
	private long amountTransferred;
	
	public int getAdminAccountNumber() {
		return adminAccountNumber;
	}
	public void setAdminAccountNumber(int adminAccountNumber) {
		this.adminAccountNumber = adminAccountNumber;
	}
	public int getCustomerAccountNumber() {
		return customerAccountNumber;
	}
	public void setCustomerAccountNumber(int customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getAmountTransferred() {
		return amountTransferred;
	}
	public void setAmountTransferred(long amountTransferred) {
		this.amountTransferred = amountTransferred;
	}
}
