package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.model.Movement;
import com.payMyBuddy.pay.service.Movementservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MovementController {

    @Autowired
    Movementservice movementservice;

    @GetMapping("/movement")
    public List<Movement> findAllMovements() {
        return movementservice.findAllMovement();
    }

    @GetMapping("movement/{id}")
    public Optional<Movement> findMovementById(Integer id) {
        return movementservice.findMovementById(id);
    }
}
