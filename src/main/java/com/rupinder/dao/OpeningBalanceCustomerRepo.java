package com.rupinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupinder.model.OpeningBalanceCustomerContainer;

public interface OpeningBalanceCustomerRepo extends JpaRepository<OpeningBalanceCustomerContainer, Integer> {

}
