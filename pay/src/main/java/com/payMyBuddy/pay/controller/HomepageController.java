package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.model.*;
import com.payMyBuddy.pay.service.AccountService;
import com.payMyBuddy.pay.service.TransactionService;
import com.payMyBuddy.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomepageController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    // Affiche le contenu de la homepage quand user se log à l'appli ou quand une transaction est faite
    // = le tableau des transactions de l'user principale + son nom et prénom + sa balance
    // Ce lance aprés une transaction effectué sur la page Transfer
    @GetMapping(value = "/homepage")
    public String homePage(@AuthenticationPrincipal User user,  Model model) {
        /*user = userService.findUserByEmail(user.getEmail());*/
        user = userService.findById(user.getId()).orElse(null);
        List<Transaction> transactions = transactionService.findTransactionsOfUserPrincipal(user);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("balance", user.getBalance());
        model.addAttribute("transactions", transactions);
        return "homepage";
    }

    // Affiche la page /transfer
    // Avec tous ses élément quand on la choisit dans le menu = nom prénom + balance + tableau des transactions de l'user principale + la liste de ses contact
    // Se lance aussi quand ajoute un nouveau contact car dans TransferController le endpoind qui le fait est redirigé vers Homepage
    @GetMapping(value = "/transfer")
    public String transferPage(@AuthenticationPrincipal User user, Model model) {
        List<Transaction> transactions = transactionService.findTransactionsOfUserPrincipal(user);
        List<Contact> contactList = userService.findContactByUserEmail(user.getEmail());
        user = userService.findUserByEmail(user.getEmail());
        user = userService.findById(user.getId()).orElse(null);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("balance", user.getBalance());
        model.addAttribute("userAddContact", userService.findUsersExceptUserPrincipal(user.getEmail())); // afficher 1ere liste déroulante choix d'un contact
        model.addAttribute("contacts", contactList); // affiche 2em liste déroulante de contact de l'user principal
        model.addAttribute("transactions", transactions);
        return "transfer" ;
    }


    // pagination transactions page transfer
   /* @GetMapping("/page/{pageNo}")
    public String findPaginatedTransfer(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<User> page = userService.findPaginatedTransfer(pageNo, pageSize);
        List<User> transactions = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("transactions", transactions);
        return "transfer";
    }*/


    // Affiche le contenu de la page profil
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

    // Pagination transaction table
    /*@GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Transaction> page = transactionService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Transaction> listTransactions = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listTransactions", listTransactions);
        return "transfer";
    }*/

}
