package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.model.Transaction;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.service.TransactionService;
import com.payMyBuddy.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping(value = "transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;


   //
    @PostMapping(value = "/transfer")
    public void transfer(@RequestParam(name = "emailSender") String emailSender, @RequestParam(name = "emailRecipient") String emailRecipient, @RequestParam(name = "date")
            LocalDate date, @RequestParam(name = "amount") Double amountTransaction, @RequestParam(name = "description") String description) {
        transactionService.transferAppli(emailSender, emailRecipient, date, amountTransaction, description);
    }

    //
    @PostMapping("/saves")
    public String saveTransaction(@AuthenticationPrincipal User user, @ModelAttribute("transactions") Transaction transaction, Model model) {
        transaction.setSenderUser(user);
        transactionService.saveTransaction(transaction);
        return "redirect:/homepage";
    }

}
