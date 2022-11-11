package com.payMyBuddy.pay.integration;

import com.payMyBuddy.pay.exception.NotCreateUserPossibleException;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.ContactRepository;
import com.payMyBuddy.pay.repository.UserRepository;
import com.payMyBuddy.pay.service.UserService;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

/*@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)*/
public class UserServiceIT {

 /*   @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setup(){
        userRepository.deleteAll();
        contactRepository.deleteAll();
    }

    @Test
    @DisplayName("Test intégration findAllUsers ")
    public void findAllUsersTest() {
        assertNotNull(userRepository.findAll());
    }


    @Test
    @DisplayName("Test findById to UserServiceIT")
    public void findByIdTestIT(){
        assertNotNull(userRepository.findById(1));
        assertNotNull(userRepository.findById(2));
    }

    @Test
    @DisplayName("Test findfindByEmailById to UserServiceIT")
    public void findByEmailTestIT(){
        assertNull(userRepository.findByEmail("nico@gmail.com"));

    }

    @Test
    @DisplayName("Test saveUser to UserService")
    void saveUserTest() {
        User user1 = new User();
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741");
        user1.setBalance(50000.00);
        userRepository.save(user1);

        List<User> userList = this.userRepository.findAll();
        assertEquals(1, userList.size());

        assert(user1.getFirstName().equals("Karl"));
        assert(user1.getLastName().equals("Gavillot"));
        assert(user1.getEmail().equals("karl@gmail.com"));
        assert(user1.getPassword().equals("P77711741"));
        assert (user1.getBalance().equals(50000.00));
    }

    @Test
    @DisplayName("Test updateUser to UserService")
    void updateUserTestIT() throws NotCreateUserPossibleException {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741");
        user1.setBalance(50000.00);
        userService.saveUser(user1);

        List<User> userList = userRepository.findAll();
        Assertions.assertTrue(userList.toString().contains("James"));
        User userUpdate = new User();
        userUpdate.setFirstName("karlito");
        userUpdate.setLastName("Gavillote");
        userUpdate.setEmail("karlito@gmail.com");
        userUpdate.setPassword("P77711741@");
        userUpdate.setBalance(50000.00);

        userService.updateUser(user1.getId(), userUpdate);
        Assertions.assertTrue(userRepository.findById(1).get().getLastName().contains("Gavillote"));
    }



    @Test
    @DisplayName("Test deleteUserByEmail to UserService")
    void deleteUserByEmailTestIT() throws NotCreateUserPossibleException {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741");
        user1.setBalance(50000.0);
        user1.setAccount(null);
        user1.setContactList(null);
        userService.saveUser(user1);

        userService.deleteUserByEmail(user1.getEmail());
        assertFalse(userRepository.findById(1).isPresent());
    }

    @Test
    @DisplayName("Test deleteUserByEmail to UserService")
    void findContactByUserIdTestIT() throws NotCreateUserPossibleException, NotCreateUserPossibleException {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741");
        user1.setBalance(50000.00);
        user1.setAccount(null);
        user1.setContactList(null);
        user1.setEnabled(true);
        user1.setRole("USER");
        userService.saveUser(user1);

        userService.findContactByUserId(user1.getId());
        assertTrue(userRepository.findById(1).isPresent());
    }

    @Test
    @DisplayName("Test deleteUserByEmail to UserService")
    void deleteContactByIDTestIT()throws NotCreateUserPossibleException {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Karl");
        user1.setLastName("Gavillot");
        user1.setEmail("karl@gmail.com");
        user1.setPassword("P77711741");
        user1.setBalance(50000.00);
        user1.setAccount(null);
        user1.setContactList(null);
        user1.setEnabled(true);
        user1.setRole("USER");
        userService.saveUser(user1);

        userService.deleteContactById(user1.getId());
        assertTrue(userRepository.findById(1).isPresent());
    }*/
}
