package com.rupinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupinder.model.ServiceCenter;

@Repository
public interface ServiceCenterRepo extends JpaRepository<ServiceCenter,Integer> {
	ServiceCenter findByCode(int code);
}