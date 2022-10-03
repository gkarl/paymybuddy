package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.exception.NotCreateUserPossibleException;
import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.Contact;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.ContactRepository;
import com.payMyBuddy.pay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    //public class UserService implements UserDetailsService

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserByIdForm(Integer id) throws NotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw  new NotFoundException("Cet ID n'a pas été trouvé " + id);
    }

    public void saveUserForm(User user) {
        userRepository.save(user);
    }

    public void deleteUserByID(Integer id) throws NotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw  new NotFoundException("Cet ID n'a pas été trouvé " + id);
        }
        userRepository.deleteById(id);
    }





    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    public void saveUser(User user) throws NotCreateUserPossibleException {
        if(user.getFirstName() != "" && user.getLastName() != "" && user.getEmail() != "" && user.getPassword()  != ""){
            List<Contact> contactList = new ArrayList<>();
            //user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setPassword(user.getPassword());
            user.setBalance(2000.0);
            user.setAccount(null);
            user.setContactList(contactList);
            user.setEnabled(true);
            userRepository.save(user);
        }
        else{
            throw new NotCreateUserPossibleException("Parameters not valid");
        }
    }


    public void updateUser(Integer id, User userUpdate)   {
        Optional<User> user = userRepository.findById(id);
        user.get().setFirstName(userUpdate.getFirstName());
        user.get().setLastName(userUpdate.getLastName());
        user.get().setEmail(userUpdate.getEmail());
        user.get().setPassword(userUpdate.getPassword());
        //return userRepository.save(user.get());
        userRepository.save(user.get());
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    public List<Contact> findContactByUserEmail(String email) {
        return contactRepository.findContactByUserEmail(email);
    }

    public List<Contact> findContactByUserId(Integer id) {
        return contactRepository.findContactByUserId(id);
    }

    /*public List<User> findUsersExceptUserPrincipal(String email) {
        List<User> userList = userRepository.findAll();
        User user = userRepository.findUsersByEmail(email);
        userList.remove(userRepository.findByEmail(email));
        for (Contact contact : user.getContactList()) {
            userList.remove(contact.getUserContact());
        }
        return userList;
    }*/

    public void saveContact(Integer userId, Integer idContact) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User don't exist"));
        User contact = userRepository.findById(idContact).orElseThrow(() -> new NotFoundException("User don't exist"));
        if (user.getId().equals(contact.getId())) {
            throw new NotFoundException("UserContact does exist!");
        }
        Contact contact1 = new Contact();
        contact1.setUser(user);
        contact1.setUserContact(contact);
        contactRepository.save(contact1);
    }

    public void deleteContactById(Integer contactId) {
        contactRepository.deleteContactById(contactId);
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }*/
}
