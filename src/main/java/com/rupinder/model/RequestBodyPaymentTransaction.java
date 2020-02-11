package com.rupinder.model;

import java.util.Date;

public class RequestBodyPaymentTransaction {
	private int adminAccountNumber;
	private int customerAccountNumber;
	private int amountTransferred;
	
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
	public int getAmountTransferred() {
		return amountTransferred;
	}
	public void setAmountTransferred(int amountTransferred) {
		this.amountTransferred = amountTransferred;
	}
}
