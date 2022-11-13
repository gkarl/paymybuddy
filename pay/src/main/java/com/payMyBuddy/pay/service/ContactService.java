package com.payMyBuddy.pay.service;

import com.payMyBuddy.pay.model.Contact;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    // Methodes passe plat **************
    List<Contact> findContactByUserEmail(String email) {
        return contactRepository.findContactByUserEmail(email);
    }

    List<Contact> findContactByUserId(Integer id) {
        return contactRepository.findContactByUserId(id);
    }

    void deleteContactById(Integer contactId) {
        contactRepository.deleteContactById(contactId);
    }

    void save(Contact contact) {
        contactRepository.save(contact);
    }

}
