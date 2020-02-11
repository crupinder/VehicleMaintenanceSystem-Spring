package com.rupinder.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rupinder.model.MaintenanceRequest;

@Repository
public interface MaintenanceRequestRepo extends JpaRepository<MaintenanceRequest,Integer> {
//	@Query("SELECT m FROM MaintenanceRequest m WHERE m.isAssigned = false AND m.serviceCenterCode = :code")
//	public List<MaintenanceRequest> getMaintenanceRequestsNotAssigned(@Param("code") int code);
	
	@Query("SELECT m FROM MaintenanceRequest m WHERE m.isAssigned = false")
	public List<MaintenanceRequest> getMaintenanceRequestsNotAssigned();
	
	@Query("SELECT m FROM MaintenanceRequest m WHERE m.employee = :employeeId")
	public List<MaintenanceRequest> getEmployeeAssignedTasks(@Param("employeeId") int id);
	
	@Query("SELECT m FROM MaintenanceRequest m WHERE m.isCompleted = true AND m.employee = :employeeId")
	public List<MaintenanceRequest> getCompletedTasks(@Param("employeeId") int id);
	
	@Query("SELECT vehicleVin FROM MaintenanceRequest m")
	public List<String> getVinsFromMaintenanceRequest();
}
