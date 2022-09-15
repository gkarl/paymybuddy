package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.service.UserService;
import lombok.Data;
import org.apache.maven.lifecycle.internal.LifecycleStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

// @Data  // je n’ai plus besoin d’écrire les getters et setters
@Controller
//@RequestMapping("/user")
public class UserController {

    //Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    UserService userService;


/*
    // get all User en forma API
    @GetMapping()
    public Iterable<User> findAllUsers(){
        return userService.findAllUsers();
    }*/

    // Afficher all user en mode appli web
    // URL /home est ou sera afficher tous les user
    // "users" est la variable qui sera utilisé en Front pour accéder au attribut du model
    // return "index" est le file html utilisé en Front
    @GetMapping("/users")
    public String findAllUsers(Model model) {
        List<User> listUser = userService.findAllUsers();
        model.addAttribute("users", listUser); // addAttribute() permet d’ajouter à mon Model un objet
        return "index";
    }


/*
    // Obtenir un utilisateur en fonction de son ID en mode API
    @GetMapping(value = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> findUserById(@PathVariable Integer id){
        return userService.findUserById(id);
    }*/


    @GetMapping("users/{id}")
    public String findUserById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user-details";
    }


    // définit l'URL de la page du formulaire pour créer un nouvelle user et la variable qui permet de récupérer les attribut du model
    @GetMapping("users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }


    // Create user en mode API
    /*@PostMapping(value = "/{id}")
    public User createUser(@PathVariable("id") Integer id, @RequestBody User user){
        return userService.updateUser(id, user);
    }*/

    // Create user en mode web Appli
    // "user/save" correspond à l'action défini au niveau du formulaire afin d'utiliser cette methode pour créer un user
    // utilise le service de création d'un user
    // RedirectAttributes .addFlashAttribute() permet d'afficher un message si la création d'un user est ok
    // redirige vers la page ou ils y a un tableau d'users /users
    @PostMapping("users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "L'utilisateur a été sauvé avec succés.");
        return "redirect:/users";
    }



    // Update User en mode Api
    /*@PutMapping(value = "/{id}")
    public User updateUser(@PathVariable("id") Integer id, @RequestBody User user){
        return userService.updateUser(id, user);
    }*/

    @GetMapping("users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findUserById(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit user (ID: " + id + ")");
            return "user_form";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message",  e.getMessage());
            return "redirect:/users";
        }
    }

    // Delete user en mode API
    /*@DeleteMapping("/{id}")
    public List<User> deleteUserById(@RequestParam("id") Integer id) {
        return userService.deleteUserById(id);
    }*/


    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }


}
