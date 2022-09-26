package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.Account;
import com.payMyBuddy.pay.model.Contact;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.ContactRepository;
import com.payMyBuddy.pay.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Test findAllUsers")
    public void findAllUsersTest() {
        User user1 = new User();
        List<Contact> contactList1 = new ArrayList<>();
        Account account1 = new Account(1, "FR9960001009530067891234001", 50000.0, user1);
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741");
        user1.setBalance(50000.0);
        user1.setAccount(account1);
        user1.setContactList(contactList1);

        User user2 = new User();
        List<Contact> contactList2 = new ArrayList<>();
        Account account2 = new Account(2, "FR9960001009530067891234002", 3000.0, user2);
        user2.setId(2);
        user2.setFirstName("Carlen");
        user2.setLastName("Laurent");
        user2.setEmail("carlen@gmail.com");
        user2.setPassword("P45061284@");
        user2.setBalance(3000.0);
        user2.setAccount(account2);
        user2.setContactList(contactList2);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        assertThat(userService.findAllUsers().toString(), containsString("Karl"));
    }

    @Test
    @DisplayName("Test findUserById")
    public void findUserByIdTest() {
        User user1 = new User();
        List<Contact> contactList1 = new ArrayList<>();
        Account account1 = new Account(1, "FR9960001009530067891234001", 50000.0, user1);
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741@");
        user1.setBalance(50000.00);
        user1.setAccount(account1);
        user1.setContactList(contactList1);

        when(userRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(user1));

        assertThat(userService.findById(any(Integer.class)).toString(), containsString("Karl"));
    }

    @Test
    @DisplayName("Test findUserByEmail")
    public void findUserByEmailTest() {
        User user1 = new User();
        List<Contact> contactList1 = new ArrayList<>();
        Account account1 = new Account(1, "FR9960001009530067891234001", 50000.0, user1);
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741@");
        user1.setBalance(50000.00);
        user1.setAccount(account1);
        user1.setContactList(contactList1);

        when(userRepository.findByEmail(any(String.class))).thenReturn(user1);

        assertEquals(userService.findUserByEmail("").getEmail(), "karl@gmail.com");

    }

    @Test
    @DisplayName("Test saveUserTest")
    public void saveUserTest() {
        User user1 = new User();
        List<Contact> contactList1 = new ArrayList<>();
        Account account1 = new Account(1, "FR9960001009530067891234001", 50000.0, user1);
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741@");
        user1.setBalance(50000.00);
        user1.setAccount(account1);
        user1.setContactList(contactList1);

        when(userRepository.save(user1)).thenReturn(user1);
        userService.saveUserForm(user1);

        verify(userRepository, times(1)).save(user1);
    }


    @Test
    @DisplayName("Test deleteUserByEmail")
    public void deleteUserByEmailTest() {
        User user1 = new User();
        List<Contact> contactList1 = new ArrayList<>();
        Account account1 = new Account(1, "FR9960001009530067891234001", 50000.0, user1);
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741@");
        user1.setBalance(50000.00);
        user1.setAccount(account1);
        user1.setContactList(contactList1);

        userService.deleteUserByEmail("karl@gmail.com");
        verify(userRepository).deleteByEmail("karl@gmail.com");
    }

   /* @Test
    @DisplayName("Test findContactByUserEmail")
    public  void findContactByUserEmailTest() {
        List<Contact> contactList = new ArrayList<>();
        when(contactRepository.findContactByUserEmail(any(String.class))).thenReturn(contactList);
        List<Contact> contactList2 = userService.findContactByUserEmail("karl@gmail.com");

        assertSame(contactList, contactList2);
        assertTrue(contactList2.isEmpty());
        verify(this.contactRepository).findContactByUserEmail(anyString());
        assertTrue(this.userService.findAllUsers().isEmpty());
    }*/

  @Test
    @DisplayName("Test findUsersExceptUserPrincipal")
    public  void findUsersExceptUserPrincipalTest() {
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

      User user2 = new User();
      Account account2 = new Account();
      user2.setId(2);
      user2.setFirstName("Carlen");
      user2.setLastName("Laurent");
      user2.setEmail("carlen@gmail.com");
      user2.setPassword("P45061284@");
      user2.setBalance(3000.0);
      user2.setAccount(new Account());
      user2.setRole("USER");
      user2.setId(2);
      user2.setContactList(new ArrayList<Contact>());
      user2.setEnabled(true);
      account2.setId(2);
      account2.setIban("FR9960001009530067891234002");
      account2.setBalance(3000.0);
      account2.setUser(user2);

      User user3 = new User();
      Account account3 = new Account();
      user3.setId(2);
      user3.setFirstName("Mathieu");
      user3.setLastName("Nebra");
      user3.setEmail("mathieu@gmail.com");
      user3.setPassword("P11111111@");
      user3.setBalance(999999.0);
      user3.setAccount(new Account());
      user3.setRole("USER");
      user3.setId(3);
      user3.setContactList(new ArrayList<Contact>());
      user3.setEnabled(true);
      account3.setId(3);
      account3.setIban("FR9960001009530067891234003");
      account3.setBalance(999999.0);
      account3.setUser(user2);

      when(userRepository.findUsersByEmail(anyString())).thenReturn(user2);
      List<User> userList = new ArrayList<User>();
      when(userRepository.findAll()).thenReturn(userList);
      List<User> userList1 = userService.findUsersExceptUserPrincipal("karl@gmail.com");
      assertSame(userList, userList1);
      assertTrue(userList1.isEmpty());

      verify(userRepository).findAll();
      verify(userRepository).findByEmail(anyString());
      verify(userRepository).findUsersByEmail(anyString());
      assertSame(userList1, userService.findAllUsers());
  }

  @Test
    @DisplayName("Test saveContact")
    public  void saveContactTest() {
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

      User user2 = new User();
      Account account2 = new Account();
      user2.setId(2);
      user2.setFirstName("Carlen");
      user2.setLastName("Laurent");
      user2.setEmail("carlen@gmail.com");
      user2.setPassword("P45061284@");
      user2.setBalance(3000.0);
      user2.setAccount(new Account());
      user2.setRole("USER");
      user2.setId(2);
      user2.setContactList(new ArrayList<Contact>());
      user2.setEnabled(true);
      account2.setId(2);
      account2.setIban("FR9960001009530067891234002");
      account2.setBalance(3000.0);
      account2.setUser(user2);

      Optional<User> userTest = Optional.<User>of(user2);
      when(userRepository.findById((Integer) org.mockito.Mockito.any())).thenReturn(userTest);
      assertThrows(NotFoundException.class, () -> this.userService.saveContact(1, 1));
      verify(this.userRepository, times(2)).findById((Integer) org.mockito.Mockito.any());
  }

}

