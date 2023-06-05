package com.test.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import com.test.entity.Transaction;
import com.test.entity.TransactionSum;

public interface TransactionService {
	Transaction createTransaction(Long transactionId, Transaction transaction);

	List<Long> getTransactionIdsByType(String type);

	List<String> getAllCurrencies();

	Transaction getTransactionById(Long transactionId) throws AccountNotFoundException;

	List<TransactionSum> getSumByTransactionId(Long transactionId);

}