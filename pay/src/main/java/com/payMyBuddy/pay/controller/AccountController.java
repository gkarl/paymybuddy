package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.model.Account;
import com.payMyBuddy.pay.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/accounts")
    public List<Account> findAllAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping(value = "/accounts/{id}")
    public Optional<Account> findAccountById(@PathVariable Integer id) {
        return accountService.findAccountById(id);
    }

    @DeleteMapping(value = "accounts/deleteContact")
    public void deleteContactById(@RequestParam("id") Integer id) {
        accountService.deleteAccountById(id);
    }
}
