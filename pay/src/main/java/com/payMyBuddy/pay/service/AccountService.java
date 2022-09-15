package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.model.Account;
import com.payMyBuddy.pay.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAllAccounts(){
        return accountRepository.findAll();
    }

    public Optional<Account> findAccountById(Integer id){
        return accountRepository.findById(id);
    }

    public void saveAccount(Integer id, Account account){
    }

    public void deleteAccountById(Integer id) {
        accountRepository.deleteById(id);
    }

}
