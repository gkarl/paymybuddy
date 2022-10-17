package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.model.Account;
import com.payMyBuddy.pay.model.Contact;
import com.payMyBuddy.pay.model.Movement;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.AccountRepository;
import com.payMyBuddy.pay.repository.MovementRepository;
import com.payMyBuddy.pay.repository.UserRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TransactionService.class, MovementService.class})
@ExtendWith(MockitoExtension.class)
public class MovementServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MovementRepository movementRepository;

    @InjectMocks
    private AccountService accountService;

    @InjectMocks
    private MovementService movementService;

    @BeforeEach
    void setup() {
        accountService = new AccountService(accountRepository, userRepository);
    }

    @Test
    @DisplayName("Test findAllMovement")
    public void findAllMovementTest() {
        List<Movement> movementList = new ArrayList<>();
        when(movementRepository.findAll()).thenReturn(movementList);
        List<Movement> movementList1 = movementService.findAllMovement();

        assertSame(movementList, movementList1);
        assertTrue(movementList1.isEmpty());
        verify(movementRepository).findAll();
    }

    @Test
    @DisplayName("Test findMovementById")
    public void findMovementByIdTest() {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741");
        user1.setBalance(50000.0);
        Movement movement = new Movement();
        movement.setId(1);
        movement.setDate(LocalDate.now());
        movement.setAmountMovement(50000.0);
        movement.setUser(user1);

        when(movementRepository.findById(1)).thenReturn(Optional.of(movement));

        assertSame(movement, this.movementService.findMovementById(1));
        verify(movementRepository).findById(any(Integer.class));
        assertTrue(movementService.findAllMovement().isEmpty());
    }

    @Test
    @DisplayName("Test transfertToApplication")
    public void  transfertToApplicationTest() {
        User user1 = new User();
        Account account1 = new Account();
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741");
        user1.setBalance(50000.0);
        user1.setAccount(new Account());
        user1.setRole("USER");
        user1.setId(1);
        user1.setContactList(new ArrayList<Contact>());
        user1.setEnabled(true);
        account1.setId(1);
        account1.setIban("FR9960001009530067891234001");
        account1.setBalance(50000.0);
        account1.setUser(user1);

        when(userRepository.findUsersByEmail(user1.getEmail())).thenReturn(user1);
        when(accountRepository.findAccountByUserEmail(user1.getEmail())).thenReturn(account1);
        movementService.transfertToApplication(user1.getEmail(), 1000.0);

        assertEquals(user1.getBalance(), 51000.0);
        assertEquals(account1.getBalance(), 49000.0);
    }

    @Test
    @DisplayName("Test transferToAccountBank")
    public  void transferToAccountBankTest() {
        User user1 = new User();
        Account account1 = new Account();
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741");
        user1.setBalance(50000.0);
        user1.setAccount(new Account());
        user1.setRole("USER");
        user1.setId(1);
        user1.setContactList(new ArrayList<Contact>());
        user1.setEnabled(true);
        account1.setId(1);
        account1.setIban("FR9960001009530067891234001");
        account1.setBalance(50000.0);
        account1.setUser(user1);

        //WHEN
        when(userRepository.findUsersByEmail(user1.getEmail())).thenReturn(user1);
        when(accountRepository.findAccountByUserEmail(user1.getEmail())).thenReturn(account1);
        movementService.transferToAccountBank(user1.getEmail(), 1000.0);
        //THEN
        assertEquals(user1.getBalance(), 49000.0);
        assertEquals(account1.getBalance(), 51000.0);
    }
}
