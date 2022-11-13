package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.service.TransactionService;
import com.payMyBuddy.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    // quand on clic sur pay dans la page transfer
    // => récupére les données entré par user dans le formulaire pour payer un contact sur la page transfer
    // transmet ses données à la methode transferAppli via arguments pour les sauver
    // calculer et sauver la nouvelle balance du sender et receiver aprés transaction
    @GetMapping(value = "/send")
    public String send(@AuthenticationPrincipal User user, @RequestParam(value = "emailContact", required = false) String emailContact, @RequestParam(value = "date", required = false) LocalDate date, @RequestParam(value = "amount", required = false) Double amountTransaction, @RequestParam(value = "description", required = false) String description) throws NotFoundException {
        transactionService.transferAppli(user.getEmail(), emailContact,date, amountTransaction, description);
        return "redirect:/homepage";
    }

    // Quand Fait "add Contact" sur la page transfer
    // va permettre de sauver un nouveau contact pour l'user principal
    @PostMapping("/saveContact")
    public String saveContact(@AuthenticationPrincipal User user, @RequestParam(value="idContact", required = false) Integer idContact) {
        userService.saveContact(user.getId(), idContact);
        return "redirect:/transfer";
    }
}
