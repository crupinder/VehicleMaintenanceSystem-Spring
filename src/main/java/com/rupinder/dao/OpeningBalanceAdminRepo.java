package com.rupinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupinder.model.OpeningBalanceAdminContainer;

@Repository
public interface OpeningBalanceAdminRepo extends JpaRepository<OpeningBalanceAdminContainer, Integer> {

}
