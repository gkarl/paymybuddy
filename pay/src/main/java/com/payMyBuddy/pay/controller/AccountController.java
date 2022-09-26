package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.model.Account;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public List<Account> findAllAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping(value = "/{email}")
    public Account findByEmail(@PathVariable String email) {
        return accountService.findAccountByEmail(email);
    }

   /* @GetMapping(value = "/{id}")
    public Account findAccountById(@PathVariable Integer id) {
        return accountService.findAccountById(id);
    }*/


    @GetMapping(value = "/addAccount")
    public String addAccount(Model model) {
        Account account = new Account();
        model.addAttribute(account);
        return "accountregistration";
    }

    @GetMapping(value = "/save")
    public String saveaccount(@AuthenticationPrincipal User user, @ModelAttribute(value = "account") Account account) {
        account.setUser(user);
        accountService.saveAccount(user.getId(), account);
        return "redirect:/profile";
    }

    @DeleteMapping(value = "/deleteAccount")
    public String deleteContactById(@RequestParam("accountId") Integer id) {
        accountService.deleteAccountById(id);
        return "redirect:/users";
    }
}
