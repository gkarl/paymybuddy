package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.model.Transaction;
import com.payMyBuddy.pay.service.Transactionalservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {

    @Autowired
    Transactionalservice transactionalservice;

    @GetMapping("transaction")
    public List<Transaction> findAllTransactions(){
        return transactionalservice.findAllTransactions();
    }

    @GetMapping(value = "transaction/{id}")
    public Optional<Transaction> findTransactionById(@PathVariable Integer id) {
        return transactionalservice.findTransactionById(id);
    }

}
