package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.model.Transaction;
import com.payMyBuddy.pay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Transactionalservice {

    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> findAllTransactions(){
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }
}
