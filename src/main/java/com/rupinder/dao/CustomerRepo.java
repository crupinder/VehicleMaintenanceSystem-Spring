package com.rupinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupinder.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer>{
	
}
