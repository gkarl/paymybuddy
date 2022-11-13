package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.Account;
import com.payMyBuddy.pay.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService    = userService;
    }

    public List<Account> findAllAccounts(){
        return accountRepository.findAll();
    }

    // quand delet un compte IBAN sur la page profil
    public  Account findAccountByEmail(String email) {
        return accountRepository.findAccountByUserEmail(email);
    }

    public Account findAccountById(Integer id) {
        return accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account doesn't exist"));
    }

    // Se lance quand on navigue vers la page Profile 1
    // ou sauve un iban
    public List<Account> findByUserId(Integer id) {
        return accountRepository.findByUserId(id);
    }

    // Se lance quand clic le bouton "Save" sur la page account/addAccount à partir de la page profil
    // permet de sauver l'IBAN entré dans le champs
    // il set la balance de ce compte à 50 000
    public void saveAccount(Integer id, Account account) {
        if (accountRepository.findAccountByUserEmail(account.getIban()) != null) {
            throw new NotFoundException("Account not find");
        }
        account.setUser(userService.findById(id).get());
        account.setBalance(50000.0);
        accountRepository.save(account);
    }

    @Transactional
    public void deleteAccountById(Integer id) {
        accountRepository.deleteById(id);
    }

    // Passe plat  **************************
    Account findAccountByUserEmail(String email) {
        return accountRepository.findAccountByUserEmail(email);
    }

    void save (Account account) {
        accountRepository.save(account);
    }

}
