package com.payMyBuddy.pay.repository;

import com.payMyBuddy.pay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository interface fourni par Spring spécifie des méthodes pour manipuler nos Entity (ex Model User)
@Repository // indique que la classe a pour rôle de communiquer avec la DB
public interface UserRepository extends JpaRepository<User, Integer> {
    public Long countById(Integer id);
}
