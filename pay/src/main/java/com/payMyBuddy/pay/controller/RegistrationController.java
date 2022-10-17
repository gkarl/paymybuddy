package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.exception.NotCreateUserPossibleException;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/registration")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "registration";
    }


    //******************
    @PostMapping(value = "/save")
    public String addUser(@Valid @ModelAttribute("user") User user, Model model) throws NotCreateUserPossibleException {
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }
}