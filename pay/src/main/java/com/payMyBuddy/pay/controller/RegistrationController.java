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

    // quand clic enregistrement on est redirigé vers la page /registration fait appel à la page registration.html
    // une nouvelle intance de user est crée des l'arrivé sur le formulaire
    @GetMapping(value = "/registration")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "registration";
    }


    // sauver les info entré dans le formulaire pour un nouvel user qui s'enregistre
    @PostMapping(value = "/save")
    public String addUser(@Valid @ModelAttribute("user") User user, Model model) throws NotCreateUserPossibleException {
        userService.saveUser(user);
        return "redirect:/login";
    }

    // retourner à la page login avec s'etre enregistré comme nouvel user
    @GetMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }
}