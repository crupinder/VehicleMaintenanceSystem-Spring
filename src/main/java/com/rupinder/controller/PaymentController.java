package com.rupinder.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupinder.dao.OpeningBalanceAdminRepo;
import com.rupinder.dao.OpeningBalanceCustomerRepo;
import com.rupinder.dao.PaymentTransactionRepo;
import com.rupinder.model.OpeningBalanceAdminContainer;
import com.rupinder.model.OpeningBalanceCustomerContainer;
import com.rupinder.model.PaymentTransaction;
import com.rupinder.model.RequestBodyPaymentTransaction;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private OpeningBalanceAdminRepo openingBalanceAdminRepo;
	
	@Autowired
	private OpeningBalanceCustomerRepo openingBalanceCustomerRepo;
	
	@Autowired
	private PaymentTransactionRepo paymentTransactionRepo;
	
	@PostMapping("/add_admin_opening_balance")
	public OpeningBalanceAdminContainer addAdminBalance(@RequestBody OpeningBalanceAdminContainer openingBalanceAdminContainer) {
		return openingBalanceAdminRepo.save(openingBalanceAdminContainer);
	}
	
	@PostMapping("/add_customer_opening_balance")
	public OpeningBalanceCustomerContainer addCustomerBalance(@RequestBody OpeningBalanceCustomerContainer openingBalanceCustomerContainer) {
		return openingBalanceCustomerRepo.save(openingBalanceCustomerContainer);
	}
	
	@PostMapping("/post_payment")
	public PaymentTransaction postPayment(@RequestBody RequestBodyPaymentTransaction requestBodyPaymentTransaction) {
		PaymentTransaction paymentTransaction = new PaymentTransaction();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();
		
	    OpeningBalanceAdminContainer openingBalanceAdminContainer = openingBalanceAdminRepo.findById(requestBodyPaymentTransaction.getAdminAccountNumber()).get();
		OpeningBalanceCustomerContainer openingBalanceCustomerContainer = openingBalanceCustomerRepo.findById(requestBodyPaymentTransaction.getCustomerAccountNumber()).get();
		openingBalanceAdminContainer.setOpeningBalance(openingBalanceAdminContainer.getOpeningBalance() + requestBodyPaymentTransaction.getAmountTransferred());
		openingBalanceCustomerContainer.setOpeningBalance(openingBalanceCustomerContainer.getOpeningBalance() - requestBodyPaymentTransaction.getAmountTransferred()); 
		
		openingBalanceAdminRepo.save(openingBalanceAdminContainer);
		openingBalanceCustomerRepo.save(openingBalanceCustomerContainer);
		
		paymentTransaction.setAdminAccountNumber(requestBodyPaymentTransaction.getAdminAccountNumber());
		paymentTransaction.setAmountTransferred(requestBodyPaymentTransaction.getAmountTransferred());
		paymentTransaction.setDate(formatter.format(date));
		paymentTransaction.setCustomerAccountNumber(requestBodyPaymentTransaction.getCustomerAccountNumber());
		
		return paymentTransactionRepo.save(paymentTransaction);
	}
}
