package com.payMyBuddy.pay.repository;

import com.payMyBuddy.pay.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findTransactionsBySenderUserEmail(String email);
    List<Transaction> findTransactionsByRecipientUserEmail(String email);

}
