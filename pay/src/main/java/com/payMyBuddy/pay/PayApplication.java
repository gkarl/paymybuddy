package com.payMyBuddy.pay;

import com.payMyBuddy.pay.model.User;
import com.payMyBuddy.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@SpringBootApplication
public class PayApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PayApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		/*System.out.println("Le password encodé affiche : " +new BCryptPasswordEncoder().encode("P77711741@"));
		byte[] passwordb = "P77711741@".getBytes(StandardCharsets.UTF_8);
		System.out.println("test pass " + passwordb);*/
		//System.out.println("test 2 : " + BCrypt.hashpw(rawPassword.toString(), salt));
		// Affiche la liste des utilisateurs existants en Base de données
		/*Iterable<User> users = userService.findAllUsers();
		users.forEach(user -> System.out.println(user.getFirstName() + " " + user.getLastName()));*/ // affiche en console la liste des utilisateurs existant en DB
		//userService.getUsers().forEach(user -> System.out.println(user.getFirstName() + " " + user.getLastName())); // idem que la ligne précédente

		// Affiche un utilisateur en particulier en fonction de son id ici on va choisir le 1er en base de données
		//Optional<User> firstUser = userService.findUserById(1); // la méthode getProductById renvoie un objet de type Optional. Cet objet permet d’encapsuler le résultat de la requête à la base de données. Dans le cas où la BDD contient un produit avec l’ID demandé, alors un objet Product sera instancié et encapsulé dans l’objet Optional
		//User userId1 = firstUser.get(); // pour récupérer l’objet encapsulé, j’utilise la méthode get().
		//System.out.println(userId1.getFirstName() + " " + userId1.getLastName() + " " + userId1.getEmail());

		// Créer un utilisateur en base de données
		//User newuser = new User(); // J’instancie un objet User et je lui définis un nom newuser
		//newuser.setFirstName("Ali"); //Je définit les valeurs de chaques attributs
		//newuser.setLastName("Aklia");
		//newuser.setEmail("ali@gmail.com");
		//newuser.setPassword("Azerty@");
		//newuser.setBalance(0.0);
		//newuser.setEnabled(true);
		//newuser.setRole("RECEIVER");
		//newuser = userService.addUser(newuser); // j'appel la méthode addUser() présent dans UserService
		//userService.getUsers().forEach(user -> System.out.println(user.getFirstName() + " " + user.getLastName())); // J’affiche les user présents en base de données, et confirme la bonne création d'un nouvelle user.


		// modifier un utilisateur déja présent dans la base de donnée
		/*User existingUser = userService.getUserById(16).get();
		existingUser.setEmail("klia@gmail.com");
		userService.addUser(existingUser);
		existingUser = userService.getUserById(16).get();
		System.out.println(existingUser.getEmail());*/


		// Supprimer un utilisateur
		/*User existingUser = userService.getUserById(12).get();
		userService.deleteUserById(12);
		userService.getUsers().forEach(user -> System.out.println(user.getFirstName() + " " + user.getLastName()));*/


		/*User newuser = new User();
		newuser.setFirstName(user.getFirstName());
		newuser.setLastName(user.getFirstName());
		newuser.setEmail(user.getEmail());
		newuser.setFirstName(user.getFirstName());
		newuser.setPassword(user.getPassword());
		newuser.setBalance(user.getBalance());
		newuser.setEnabled(user.isEnabled());
		newuser.setRole(user.getRole());*/
	}
}
