package com.payMyBuddy.pay.repository;

import com.payMyBuddy.pay.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Integer>{


}
