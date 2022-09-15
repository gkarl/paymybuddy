package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.model.Movement;
import com.payMyBuddy.pay.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Movementservice {

    @Autowired
    MovementRepository movementRepository;

    public List<Movement> findAllMovement(){
        return movementRepository.findAll();
    }

    public Optional<Movement> findMovementById(Integer id){
        return movementRepository.findById(id);
    }
}
