package com.rupinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupinder.model.PaymentTransaction;

@Repository
public interface PaymentTransactionRepo extends JpaRepository<PaymentTransaction, Integer> {

}
