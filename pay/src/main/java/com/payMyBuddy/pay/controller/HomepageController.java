package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.model.Contact;
import com.payMyBuddy.pay.model.Transaction;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.service.AccountService;
import com.payMyBuddy.pay.service.TransactionService;
import com.payMyBuddy.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomepageController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/transfer")
    public String transferPage(@ModelAttribute User user, Model model) {
        List<Transaction> transactions = transactionService.findTransactionsOfUserPrincipal(user);
        List<Contact> contactList = userService.findContactByUserEmail(user.getEmail());
        model.addAttribute("user", user.getFirstName());// Welcome + FirstName
        model.addAttribute("balance", user.getBalance());// Amount of balance
        model.addAttribute("userAddContact", userService.findUsersExceptUserPrincipal(user.getEmail())); // Add list contact
        model.addAttribute("contacts", contactList); // List contacts for send money
        model.addAttribute("transactions", transactions); // List of transactions
        return "transfer";
    }
}
