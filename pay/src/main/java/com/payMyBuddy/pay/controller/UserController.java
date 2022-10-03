package com.payMyBuddy.pay.controller;

import com.payMyBuddy.pay.exception.NotFoundException;
import com.payMyBuddy.pay.model.Contact;
import com.payMyBuddy.pay.model.Transaction;
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




    // Afficher all user en mode appli web
    // URL /users est ou sera afficher tous les user
    // "users" est la variable qui sera utilisé en Front pour accéder au attribut du model
    // return "index" est le file html utilisé en Front
    @GetMapping("/users")
    public String findAllUsers(Model model) {
        List<User> listUser = userService.findAllUsers();
        model.addAttribute("users", listUser); // addAttribute() permet d’ajouter à mon Model un objet
        return "user";
    }



    @GetMapping("users/{id}")
    public String findUserById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.findUserByIdForm(id));
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
            User user = userService.findUserByIdForm(id);
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
    public String deleteUserForm(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUserByID(id);
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }

    //*****

    /*@GetMapping()
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
    @GetMapping(value = "/deleteContact")
    public String deleteContact(@RequestParam("contactId") Integer contactId) {
        userService.deleteContactById(contactId);
        return "redirect:/profile";
    }*/

}
