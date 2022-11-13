package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.exception.NotCreateUserPossibleException;
import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.Contact;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    //public class UserService implements UserDetailsService

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactService contactService;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService() {
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    // pagination
    public Page<User> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.userRepository.findAll(pageable);
    }

    public void saveUserForm(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Integer id) throws NotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw  new NotFoundException("Cet ID n'a pas été trouvé " + id);
        }
        userRepository.deleteById(id);
    }

//*******************************

    // etape 2 login
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
    
    public User findUserByEmail(String email) throws NotFoundException{
        return userRepository.findByEmail(email);
    }

    // Quand on s'enregistre comme nouvelle utilisateur au lancement de l'application
    // Permet de sauver les données entré dans le formulaire de ce nouvelle user
    public void saveUser(User user) throws NotCreateUserPossibleException {
        if(user.getFirstName() != "" && user.getLastName() != "" && user.getEmail() != "" && user.getPassword()  != ""){
            List<Contact> contactList = new ArrayList<>();
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setPassword(user.getPassword());
            user.setBalance(2000.0);
            user.setAccount(null);
            user.setContactList(contactList);
            user.setEnabled(true);
            user.setRole("USER");
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
        userRepository.save(user.get());
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    // quand va à la page transfer
    // => recherche les contact de l'user principale grace à son email
    // (2em liste page transfer)
    // se lance aussi quand ajoute un nouveau contact
    public List<Contact> findContactByUserEmail(String email) {
        return contactService.findContactByUserEmail(email);
    }

    // Se lance quand on navigue vers la page Profile 2
    public List<Contact> findContactByUserId(Integer id) {
        return contactService.findContactByUserId(id);
    }

    // Quand user navigue vers la page Transfer
    // => Affiche la liste des user de l'appli excepté l'user principale pour lui permettre de rajouter un contact par la suite
    // sur la page transfer 1ere liste choix d'un nouveau contact
    public List<User> findUsersExceptUserPrincipal(String email) {
        List<User> userList = userRepository.findAll();
        User user = userRepository.findUsersByEmail(email);
        userList.remove(userRepository.findByEmail(email));
        for (Contact contact : user.getContactList()) {
            userList.remove(contact.getUserContact());
        }
        return userList;
    }

    // Quand fait "Add Contact sur la page Transfer
    // permet de sauver un nouveau contact choisit dans une liste déroulante à attribuer à user principal
    @Transactional
    public void saveContact(Integer userId, Integer idContact) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User don't exist"));
        User contact = userRepository.findById(idContact).orElseThrow(() -> new NotFoundException("User don't exist"));
        if (user.getId().equals(contact.getId())) {
            throw new NotFoundException("UserContact does exist!");
        }
        Contact contact1 = new Contact();
        contact1.setUser(user);
        contact1.setUserContact(contact);
        userRepository.findById(userId);
        contactService.save(contact1);
    }

    @Transactional
    public void deleteContactById(Integer contactId) {
        contactService.deleteContactById(contactId);
    }

    // Passe plat  **************************
    User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    void save(User user) {
        userRepository.save(user);
    }

    User findUsersByEmail(String email) {
        return userRepository.findUsersByEmail(email);
    }

    // méthode de l'interface UserDetailsService utilisé par Spring Security utilisé au moment ou un utilisateur se log à l'appli avec son email et mot de passe
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }


}
