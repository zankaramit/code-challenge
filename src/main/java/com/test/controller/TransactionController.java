package com.test.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.Nullable;
import com.test.entity.Transaction;
import com.test.entity.TransactionSum;
import com.test.service.TransactionService;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/bookingservice")
public class TransactionController {
	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PutMapping("/transaction/{transactionId}")
	public ResponseEntity<Transaction> createTransaction(@PathVariable @Nullable Long transactionId,
			@RequestBody Transaction transaction) {
		
		Transaction createTransaction = transactionService.createTransaction(transactionId, transaction);
		return new ResponseEntity<Transaction>(createTransaction, HttpStatus.CREATED);

	}

	@GetMapping("/transaction/{transactionId}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable Long transactionId) throws AccountNotFoundException {
		Transaction transaction = transactionService.getTransactionById(transactionId);
		return new ResponseEntity<Transaction>(transaction,HttpStatus.OK);
	}

	@GetMapping("/types/{type}")
	public List<Long> getTransactionIdsByType(@PathVariable("type") String type) {
		return transactionService.getTransactionIdsByType(type);
	}

	@GetMapping("/currencies")
	public List<String> getAllCurrencies() {
		List<String> allCurrencies = transactionService.getAllCurrencies();
		return allCurrencies;
	}

	@GetMapping("/sum/{transactionId}")
	public List<TransactionSum> getSumByTransactionId(@PathVariable("transactionId") Long transactionId) {
		return transactionService.getSumByTransactionId(transactionId);
	}
}