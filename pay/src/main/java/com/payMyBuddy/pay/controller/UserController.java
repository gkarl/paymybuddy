package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.Contact;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    // endpoints page users mais avec pagination
    @GetMapping("/users")
    public String findAllUsers(@AuthenticationPrincipal User user, Model model) {
        return findPaginated(1, model);
    }

    // Pagination transaction table ok sans sort
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<User> page = userService.findPaginated(pageNo, pageSize);
        List<User> users = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("users", users);
        return "user";
    }

    // pas besoin
    @GetMapping("users/{id}")
    public String findUserById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-details";
    }

    // définit l'URL de la page du formulaire pour créer un nouvelle user et la variable qui permet de récupérer les attribut du model
    @GetMapping("users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    // Create user en mode web Appli
    // "user/save" correspond à l'action défini au niveau du formulaire afin d'utiliser cette methode pour créer un user
    // utilise le service de création d'un user
    // RedirectAttributes .addFlashAttribute() permet d'afficher un message si la création d'un user est ok
    // redirige vers la page ou ils y a un tableau d'users /users
    @PostMapping("users/save")
    public String saveUserForm(User user, RedirectAttributes redirectAttributes) {
        userService.saveUserForm(user);
        redirectAttributes.addFlashAttribute("message", "L'utilisateur a été sauvé avec succés.");
        return "redirect:/users";
    }

    // edit un user
    @GetMapping("users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<User> user = userService.findById(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit user (ID: " + id + ")");
            return "user_form";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message",  e.getMessage());
            return "redirect:/users";
        }
    }

    // delete un user
    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }

    //*****

    @GetMapping()
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PutMapping(value = "/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @GetMapping(value = "/contacts/{email}")
    public List<Contact> findContactByUserEmail(@PathVariable String email) {
        return userService.findContactByUserEmail(email);
    }

    @GetMapping("/user/deleteContact/{id}")
    public String deleteContactUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteContactById(id);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/profile";
    }


}
