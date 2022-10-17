package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.model.*;
import com.payMyBuddy.pay.service.AccountService;
import com.payMyBuddy.pay.service.TransactionService;
import com.payMyBuddy.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping(value = "/homepage")
    public String homePage(@AuthenticationPrincipal User user, Model model) {
        List<Transaction> transactions = transactionService.findTransactionsOfUserPrincipal(user);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("balance", user.getBalance());
        model.addAttribute("transactions", transactions);
        return "homepage";
    }

    @GetMapping(value = "/transfer")
    public String transferPage(@AuthenticationPrincipal User user, Model model) {
        List<Transaction> transactions = transactionService.findTransactionsOfUserPrincipal(user);
        List<Contact> contactList = userService.findContactByUserEmail(user.getEmail());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("balance", user.getBalance());
        model.addAttribute("userAddContact", userService.findUsersExceptUserPrincipal(user.getEmail())); // Add list contact
        model.addAttribute("contacts", contactList);
        model.addAttribute("transactions", transactions);
        return "transfer";
    }


    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {
        List<Account> userAccountList = accountService.findByUserId(user.getId());
        List<Contact> contactList = userService.findContactByUserId(user.getId());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("contacts", contactList);
        model.addAttribute("accounts", userAccountList);
        model.addAttribute("movements", new Movement());
        return "profile";
    }

}
