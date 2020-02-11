package com.rupinder.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rupinder.dao.MaintenanceRequestRepo;
import com.rupinder.model.Employee;
import com.rupinder.model.MaintenanceRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaintenanceRequestControllerTest {
	@Autowired
	private MaintenanceRequestController maintenanceRequestController;
	
	@MockBean
	private MaintenanceRequestRepo repository;
	
	@Test
	public void createRequestTest() {
		MaintenanceRequest m = new MaintenanceRequest("Oil Change", "12", 1002, false, false, new Employee());
		when(repository.save(m)).thenReturn(m);
		assertEquals(m, maintenanceRequestController.createRequest(m));
	}
	
	@Test
	public void getPendingCompleteRequestsTest() {
		MaintenanceRequest m = new MaintenanceRequest("Oil Change", "12", 1002, false, false, new Employee());
		when(repository.findAll()).thenReturn(Stream.of(new MaintenanceRequest("Oil Change", "12", 1002, false, false, new Employee())).collect(Collectors.toList()));
		assertEquals(1, maintenanceRequestController.getPendingCompleteRequests().size());
	}
	
//	@Mock
//	private MaintenanceRequest maintenanceRequest;
//	
//	@Mock
//	MaintenanceRequestRepo maintenanceRequestRepo;
//	
//	@InjectMocks
//	private MaintenanceRequestController maintenanceRequestController;
//	
//	@Before
//	public void setup() {
//		when(maintenanceRequestRepo.save(maintenanceRequest)).thenReturn(maintenanceRequest);
//	}
//	
//	@Test
//	public void testMaintenanceRequest() {
//		assertThat(maintenanceRequestController.createRequest(maintenanceRequest), is(maintenanceRequest));
//	}
}

