package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.model.Account;
import com.payMyBuddy.pay.model.Contact;
import com.payMyBuddy.pay.model.Transaction;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.AccountRepository;
import com.payMyBuddy.pay.repository.TransactionRepository;
import com.payMyBuddy.pay.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {TransactionService.class})
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private AccountService accountService;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        accountService = new AccountService(accountRepository, userRepository);
    }

    @Test
    @DisplayName("Test findTransactionsOfUserPrincipal")
    public  void findTransactionsOfUserPrincipalTest() {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741@");
        user1.setBalance(50000.00);
        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setDate(LocalDate.now());
        transaction.setAmount(100.0);
        transaction.setDescription("Rembousement de frais");
        transaction.setSenderUser(new User());
        transaction.setRecipientUser(new User());
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        when(transactionRepository.findTransactionsBySenderUserEmail(user1.getEmail())).thenReturn(transactionList);
        Assertions.assertTrue(transactionService.findTransactionsOfUserPrincipal(user1).size() > 0);
    }

    @Test
    @DisplayName("Test saveTransaction")
    public  void saveTransactionTest() {
        User user1 = new User();
        Account account1 = new Account();
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741@");
        user1.setBalance(50000.00);
        user1.setAccount(new Account());
        user1.setRole("USER");
        user1.setContactList(new ArrayList<Contact>());
        user1.setEnabled(true);
        account1.setId(1);
        account1.setIban("FR9960001009530067891234001");
        account1.setBalance(50.0);
        account1.setUser(user1);

        User user2 = new User();
        Account account2 = new Account();
        user2.setId(2);
        user2.setFirstName("Carlen");
        user2.setLastName("Laurent");
        user2.setEmail("carlen@gmail.com");
        user2.setPassword("P45061284@");
        user2.setBalance(3000.00);
        user2.setAccount(new Account());
        user2.setRole("USER");
        user2.setContactList(new ArrayList<Contact>());
        user2.setEnabled(true);
        account2.setId(2);
        account2.setIban("FR9960001009530067891234002");
        account2.setBalance(30.0);
        account2.setUser(user2);

        Transaction transaction = new Transaction();
        transaction.setDate(LocalDate.now());
        transaction.setSenderUser(user1);
        transaction.setRecipientUser(user1);
        transaction.setAmount(100.0);
        transaction.setId(1);
        transaction.setDescription("Rembousement de frais");

        when(transactionRepository.save((Transaction) any())).thenReturn(transaction);
        assertSame(transaction, transactionService.saveTransaction(new Transaction()));
        verify(transactionRepository).save((Transaction) any());
    }


   /* @Test
    @DisplayName("Test transfer")
    public  void transferTest() {
        User user1 = new User();
        Account account1 = new Account();
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741@");
        user1.setBalance(50000.00);
        user1.setAccount(new Account());
        user1.setRole("USER");
        user1.setContactList(new ArrayList<Contact>());
        user1.setEnabled(true);
        account1.setId(1);
        account1.setIban("FR9960001009530067891234001");
        account1.setBalance(100.00);
        account1.setUser(user1);

        User user2 = new User();
        Account account2 = new Account();
        user2.setId(2);
        user2.setFirstName("Carlen");
        user2.setLastName("Laurent");
        user2.setEmail("carlen@gmail.com");
        user2.setPassword("P45061284@");
        user2.setBalance(3000.00);
        user2.setAccount(new Account());
        user2.setRole("USER");
        user2.setContactList(new ArrayList<Contact>());
        user2.setEnabled(true);
        account2.setId(2);
        account2.setIban("FR9960001009530067891234002");
        account2.setBalance(3000.00);
        account2.setUser(user2);

        when(userRepository.findByEmail(user1.getEmail())).thenReturn(user1);
        assertThrows(RuntimeException.class, () -> transactionService.transfer("karl@gmail.com", "carlen@gmail.com", LocalDate.now(), 100.0, "Rembousement de frais"));
        verify(userRepository, times(2)).findByEmail(anyString());
    }*/
}
