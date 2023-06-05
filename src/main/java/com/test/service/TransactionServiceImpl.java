package com.test.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Transaction;
import com.test.entity.TransactionSum;
import com.test.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public Transaction createTransaction(Long transactionId, Transaction transaction) {
		if (transactionId == null) {
			transaction.setTransactionId(transactionId);
			return transactionRepository.save(transaction);
		} else {
			transaction.setTransactionId(transactionId);
			return transactionRepository.save(transaction);
		}
	}

	@Override
	public Transaction getTransactionById(Long transactionId) throws AccountNotFoundException {
		Optional<Transaction> findById = transactionRepository.findById(transactionId);

		if (!findById.isPresent()) {

			throw new AccountNotFoundException("Transaction with ID " + transactionId + " not found.");

		} else {
			return findById.get();
		}

	}

	@Override
	public List<Long> getTransactionIdsByType(String type) {
		List<Long> transactionIds = new ArrayList<>();
		List<Transaction> transactions = transactionRepository.findByType(type);

		for (Transaction transaction : transactions) {
			transactionIds.add(transaction.getTransactionId());
		}

		return transactionIds;
	}

	@Override
	public List<String> getAllCurrencies() {

		List<Transaction> transactions = transactionRepository.findAll();
		Set<String> currencies = new HashSet<>();

		for (Transaction transaction : transactions) {
			currencies.add(transaction.getCurrency());
		}

		return new ArrayList<>(currencies);
	}

	@Override
	public List<TransactionSum> getSumByTransactionId(Long transactionId) {
		List<Transaction> linkedTransactionsList = transactionRepository.findByParentIdTransactionId(transactionId);
		
	    Map<String, List<Transaction>> transactionsByCurrency = linkedTransactionsList.stream()
	            .collect(Collectors.groupingBy(Transaction::getCurrency));

	    List<TransactionSum> transactionSumList = new ArrayList<>();
	    

	    transactionsByCurrency.forEach((currency, transactions) -> {
	        double sum = transactions.stream()
	                .mapToDouble(Transaction::getAmount)
	                .sum();

	        TransactionSum transactionSum = new TransactionSum();
	        transactionSum.setCurrency(currency);
	        transactionSum.setSum(sum);

	        transactionSumList.add(transactionSum);
	    });

	    return transactionSumList;
	}		
	

}
