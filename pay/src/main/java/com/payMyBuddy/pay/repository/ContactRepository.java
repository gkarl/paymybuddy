package com.payMyBuddy.pay.repository;

import com.payMyBuddy.pay.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository <Contact, Integer>{

    List<Contact> findContactByUserEmail(String email);
    List<Contact> findContactByUserId(Integer id);
    void deleteContactById(Integer contactId);
}
