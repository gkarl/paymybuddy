package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.Account;
import com.payMyBuddy.pay.repository.AccountRepository;
import com.payMyBuddy.pay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;


    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository    = userRepository;
    }

    public List<Account> findAllAccounts(){
        return accountRepository.findAll();
    }

    public  Account findAccountByEmail(String email) {
        return accountRepository.findAccountByUserEmail(email);
    }

    public Account findAccountById(Integer id) {
        return accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account doesn't exist"));
    }

    public List<Account> findByUserId(Integer id) {
        return accountRepository.findByUserId(id);
    }

    public void saveAccount(Integer id, Account account) {
        if (accountRepository.findAccountByUserEmail(account.getIban()) != null) {
            throw new NotFoundException("Account not find");
        }
        account.setUser(userRepository.findById(id).get());
        account.setBalance(100000.0);
        accountRepository.save(account);
    }

    public void deleteAccountById(Integer id) {
        accountRepository.deleteById(id);
    }


}
