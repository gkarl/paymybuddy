package com.payMyBuddy.pay.repository;

import com.payMyBuddy.pay.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer idAccount);

    Account findAccountByUserEmail(String email);
    List<Account> findByUserId(Integer id);
}
