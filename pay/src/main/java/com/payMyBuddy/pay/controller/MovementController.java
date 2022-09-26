package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.model.Movement;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value= "/movement")
public class MovementController {

    @Autowired
    MovementService movementservice;

    @GetMapping
    public List<Movement> findAllMovements() {
        return movementservice.findAllMovement();
    }

 /*   @GetMapping("movement/{id}")
    public Movement findMovementById(Integer id) {
        return movementservice.findMovementById(id);
    }*/

    @GetMapping(value = "/transferToApplication")
    public String transferToApplication(@AuthenticationPrincipal User user, @RequestParam(value= "amount") Double amountMovement) {
        movementservice.transfertToApplication(user.getEmail(), amountMovement);
        return "redirect:/profile";
    }

    @GetMapping(value = "/transferToAccountBank")
    public String transferToAccountBank(@AuthenticationPrincipal User user,  @RequestParam(value= "amount") Double amountMovement) {
        movementservice.transferToAccountBank(user.getEmail(), amountMovement);
        return "redirect:/profile";
    }
}
