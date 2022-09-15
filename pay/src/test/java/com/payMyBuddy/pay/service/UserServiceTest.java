package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.model.User;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Test getUsers")
    public void getUsersTest() {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P777");
        user1.setBalance(1000000000.0);

        User user2 = new User();
        user2.setId(1);
        user2.setFirstName("Carlen");
        user2.setLastName("Laurent");
        user2.setEmail("carlen@gmail.com");
        user2.setPassword("P111");
        user2.setBalance(100.0);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        assertThat(userService.findAllUsers().toString(), containsString("Karl"));
    }

    @Test
    @DisplayName("Test findUserById")
    public void findUserByIdTest() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Karl");
        user.setLastName("Gavillot");
        user.setEmail("karl@gmail.com");
        user.setPassword("P77711741@");
        user.setBalance(50000.00);
        user.setEnabled(true);
        user.setRole("Receiver");

        when(userRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(user));

        assertThat(userService.findUserById(any(Integer.class)).toString(), containsString("Karl"));
    }

    @Test
    @DisplayName("Test saveUserTest")
    public void saveUserTest() {
        User user = new User();
        user.setId(19);
        user.setFirstName("Nicola");
        user.setLastName("Maduro");
        user.setEmail("nicola@gmail.com");
        user.setPassword("P45111213@");
        user.setBalance(500.00);
        user.setEnabled(true);
        user.setRole("Receiver");

        when(userRepository.save(user)).thenReturn(user);
        userService.saveUser(user);

        verify(userRepository, times(1)).save(user);
    }


}

