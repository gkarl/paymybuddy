package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.Account;
import com.payMyBuddy.pay.model.Movement;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovementService {

    @Autowired
    MovementRepository movementRepository;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;


    public MovementService(MovementRepository movementRepository, UserService userService) {
        this.movementRepository = movementRepository;
        this.userService = userService;
    }

    public List<Movement> findAllMovement(){
        return movementRepository.findAll();
    }

    public Movement findMovementById(Integer id){
        return movementRepository.findById(id).orElseThrow(() -> new NotFoundException("Movement doesn't exist"));
    }

    // transférer de l'argent de son compte en banque vers l'application Paymybuddy sur la page Profil
    @Transactional
    public void transfertToApplication(String emailUser, Double amountMovement) {
        User user = userService.findUsersByEmail(emailUser);
        user.setBalance(user.getBalance() + amountMovement);
        userService.save(user);
        Account account = accountService.findAccountByUserEmail(emailUser);
        account.setBalance(account.getBalance() - amountMovement);
        accountService.save(account);
    }

    // transférer de l'agent de son compte PaymyBuddy (balance) vers son compte en banque
    @Transactional
    public void transferToAccountBank(String emailUser, Double amountMovement) {
        User user = userService.findUsersByEmail(emailUser);
        user.setBalance(user.getBalance() - amountMovement);
        userService.save(user);
        Account account = accountService.findAccountByUserEmail(emailUser);
        account.setBalance(account.getBalance() + amountMovement);
        accountService.save(account);
    }
}
