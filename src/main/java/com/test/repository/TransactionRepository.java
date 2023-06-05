package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByType(String type);

	List<Transaction> findByParentIdTransactionId(Long transactionId);

}