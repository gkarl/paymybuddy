package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Test findAllUsers")
    public void findAllUsersTest() throws Exception{
        User user = new User();
        List<User> listUser = new ArrayList<>();
        user.setFirstName("Pierre");
        user.setLastName("Benasailla");
        user.setEmail("pierre.gmail.com");
        user.setPassword("P968574@");
        user.setBalance(100.00);
        user.setRole("RECEIVER");
        user.setEnabled(true);
        listUser.add(user);
        when(userService.findAllUsers()).thenReturn(listUser);
        mockMvc.perform(get("/users")).andExpect(status().isOk());
        verify(userService, times(1)).findAllUsers();
    }

    @Test
    @DisplayName("Test saveUserForm")
    public void showNewFormTest() throws Exception {
        mockMvc.perform(get("/users/new")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test showEditForm")
    public  void showEditFormTest() throws Exception {
       /* User user = new User();
        List<User> listUser = new ArrayList<>();
        user.setId(2);
        user.setFirstName("Carlen");
        user.setLastName("Laurent");
        user.setEmail("carlen@gmail.com");
        user.setPassword("P45061284");
        user.setBalance(3000.00);
        user.setRole("RECEIVER");
        user.setEnabled(true);
        listUser.add(user);
        when(userService.findAllUsers()).thenReturn(listUser);*/
        //mockMvc.perform(get("/users/edit/6")).andExpect(status().isOk());
        //verify(userService, times(1)).findUserByIdForm(2);
    }


    @Test
    @DisplayName("Test saveUserForm")
    public void saveUserFormTest() throws Exception {
    /*    User user = new User();
        user.setId(2);
        user.setFirstName("Carleno");
        user.setLastName("Laurento");
        user.setEmail("carleno@gmail.com");
        user.setPassword("P45061289");
        user.setBalance(3000.00);
        user.setRole("RECEIVER");
        user.setEnabled(true);
        mockMvc.perform(get("/users/save")).andExpect(status().isOk());
        verify(userService, times(1)).saveUserForm(user);*/
    }

    @Test
    @DisplayName("Test deleteUserForm")
    public void deleteUserFormTest() throws Exception {

        mockMvc.perform(get("/users/delete/2")).andExpect(status().isOk());
    }

}
