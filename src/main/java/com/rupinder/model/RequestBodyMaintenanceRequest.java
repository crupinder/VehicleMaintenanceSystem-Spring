package com.rupinder.model;

public class RequestBodyMaintenanceRequest {
	private int customerId;
	private String vehicleVin;
	private int serviceCenterCode;
	private String serviceType;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerID(int customerId) {
		this.customerId = customerId;
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
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
}
