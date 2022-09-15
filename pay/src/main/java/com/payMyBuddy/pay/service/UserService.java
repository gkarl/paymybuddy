package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    /*
    // Mode API
    public Optional<User> findUserById(Integer id){
        return userRepository.findById(id);
    }*/

    public User findUserById(Integer id) throws NotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw  new NotFoundException("Cet ID n'a pas été trouvé " + id);
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }

    //utilisé pour updater un User en mode API
    /*public User updateUser(Integer id, User userUpdate){
        Optional<User> user = userRepository.findById(id);
        user.get().setFirstName(userUpdate.getFirstName());
        user.get().setLastName(userUpdate.getLastName());
        user.get().setEmail(userUpdate.getEmail());
        user.get().setPassword(userUpdate.getPassword());
        return userRepository.save(user.get());
    }*/

    // utilisé pour API delete user
    /*public List<User> deleteUserById(Integer id) {
        userRepository.deleteById(id);
        return userRepository.findAll();
    }*/

    public void deleteUser(Integer id) throws NotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw  new NotFoundException("Cet ID n'a pas été trouvé " + id);
        }
        userRepository.deleteById(id);
    }
}
