package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.Account;
import com.payMyBuddy.pay.model.Movement;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.AccountRepository;
import com.payMyBuddy.pay.repository.MovementRepository;
import com.payMyBuddy.pay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovementService {

    @Autowired
    MovementRepository movementRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

 /*   @Autowired
    MovementService movementService;*/

    public List<Movement> findAllMovement(){
        return movementRepository.findAll();
    }

    public Movement findMovementById(Integer id){
        return movementRepository.findById(id).orElseThrow(() -> new NotFoundException("Movement doesn't exist"));
    }

    public void transfertToApplication(String emailUser, Double amountMovement) {
        User user = userRepository.findUsersByEmail(emailUser);
        user.setBalance(user.getBalance() + amountMovement);
        userRepository.save(user);
        Account account = accountRepository.findAccountByUserEmail(emailUser);
        account.setBalance(account.getBalance() - amountMovement);
        accountRepository.save(account);
    }

    public void transferToAccountBank(String emailUser, Double amountMovement) {
        User user = userRepository.findUsersByEmail(emailUser);
        user.setBalance(user.getBalance() - amountMovement);
        userRepository.save(user);
        Account account = accountRepository.findAccountByUserEmail(emailUser);
        account.setBalance(account.getBalance() + amountMovement);
        accountRepository.save(account);
    }
}
