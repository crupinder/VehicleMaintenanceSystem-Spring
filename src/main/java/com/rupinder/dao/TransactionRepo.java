package com.rupinder.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rupinder.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
	@Query("SELECT t FROM Transaction t WHERE t.customer.id = :custId")
	public List<Transaction> getAllTransactions(@Param("custId") Integer id);
}
