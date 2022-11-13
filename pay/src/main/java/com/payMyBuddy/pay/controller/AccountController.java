package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.Account;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    // Affiche tous les comptes de la personne
    @GetMapping
    public List<Account> findAllAccounts() {
        return accountService.findAllAccounts();
    }

    // quand delet un compte IBAN sur la page profil
    @GetMapping(value = "/{email}")
    public Account findByEmail(@PathVariable String email) {
        return accountService.findAccountByEmail(email);
    }

    // Affiche la page pour enregistrer l'IBAN d'un compte en banque
    // => clic "Add Iban Account"
    @GetMapping(value = "/addAccount")
    public String addAccount(Model model) {
        Account account = new Account();
        model.addAttribute(account);
        return "accountRegistration";
    }

    // sauver un compte Iban quand clic le bouton save sur la page account/addAccount (le endpoint précedent)
    @GetMapping(value = "/save")
    public String saveAccount(@AuthenticationPrincipal User user, @ModelAttribute(value = "account") Account account) {
        account.setUser(user);
        accountService.saveAccount(user.getId(), account);
        return "redirect:/profile";
    }

    // delet un iban
    @GetMapping(value = "/deleteAccount/{id}")
    public String deleteContactById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            accountService.deleteAccountById(id);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/profile";
    }
}
