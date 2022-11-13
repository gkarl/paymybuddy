package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.model.Account;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.AccountRepository;
import com.payMyBuddy.pay.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountService accountService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        accountService = new AccountService(accountRepository, userService);
    }

    @Test
    @DisplayName("Test findAllAccounts")
    public void findAllAccountsTest() {
        List<Account> accountList = new ArrayList<>();
        Account       account1    = new Account();
        account1.setId(1);
        account1.setIban("FR9960001009530067891234001");
        account1.setUser(userRepository.getById(1));
        accountList.add(account1);
        Account account2 = new Account();
        account2.setId(2);
        account2.setIban("FR9960001009530067891234003");
        account2.setUser(userRepository.getById(2));
        accountList.add(account2);

        when(accountRepository.findAll()).thenReturn(accountList);
        Assertions.assertThat(accountService.findAllAccounts().size() == 2);
    }

    @Test
    @DisplayName("Test findAccountById")
    public void findAccountByIdTest() {
        Account account1 = new Account();
        account1.setId(1);
        account1.setIban("FR9960001009530067891234001");
        account1.setUser(userRepository.getById(1));
        when(accountRepository.findById(1)).thenReturn(java.util.Optional.of(account1));
        assertEquals(accountService.findAccountById(1).getId(), 1);
    }

    @Test
    @DisplayName("Test findAccountByEmail")
    public void findAccountByEmailTest() {
        Account account1 = new Account();
        account1.setId(1);
        account1.setIban("FR9960001009530067891234001");
        account1.setUser(userRepository.getById(1));

        when(accountRepository.findAccountByUserEmail(any(String.class))).thenReturn(account1);
        assertEquals(accountService.findAccountByEmail("").getIban(), "FR9960001009530067891234001");
    }

    @Test
    @DisplayName("Test saveAccount")
    public void  saveAccountTest() {
        User user = new User();
        user.setId(1);
        Account account1 = new Account();
        account1.setId(1);
        account1.setIban("FR9960001009530067891234001");
        account1.setUser(user);

        when(accountRepository.save(account1)).thenReturn(account1);
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        accountService.saveAccount(1, account1);
        verify(userRepository, times(1)).findById(1);
        verify(accountRepository, times(1)).save(account1);
    }

    @Test
    @DisplayName("Test deleteAccountById")
    public  void deleteAccountByIdTest() {
        User    user     = new User();
        user.setId(1);
        Account account1 = new Account();
        account1.setId(1);
        account1.setIban("FR9960001009530067891234001");
        account1.setUser(user);

        accountService.deleteAccountById(1);
        verify(accountRepository).deleteById(1);
    }




}
