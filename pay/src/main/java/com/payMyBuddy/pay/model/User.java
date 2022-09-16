package com.payMyBuddy.pay.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
@Entity   // Indique que la classe correspond à une table de la base de données
@DynamicUpdate  // Si un Update d'une ligne de la table evite de faire un update de tous les attributs mais que celui qui est modifié
@Table(name = "user")  // Indique le nom de la table associée permet de faire une association entre une Classe et une Table de la DB
public class User {

    // Attributs de la Classe qui vont correspondre aux colonnes de la table user
    @Id // Clé primaire de la table user
    @GeneratedValue(strategy = GenerationType.IDENTITY) // lié au fait que l'id est auto incrémenté
    @Column(name = "id") // Permet de faire l'association avec le nom de la colonne dans la DB user
    private Integer id;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Double balance;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL // Si je supprime l'user ça supprime son account, si je met ajour user ça mettra ajour account associé
    )
    private Account account;

    @OneToMany(
            mappedBy = "user", // nom donnée à l'attribut correspondant dans la Class Contact
            fetch = FetchType.EAGER // Lorsqu'on va récupérer l'user tous ses contacts associés seront récupérés
    )
    private List<Contact> contactList = new ArrayList<>();

    private boolean enabled;
    private String role;


    public User(){

    }

    public User(Integer id, String firstName, String lastName, String email, String password, Double balance, boolean enabled, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.enabled = enabled;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public String toString() {
        return "User [id=" + getId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", email=" + getEmail() + ", password=" + getPassword() + ", balance=" + getBalance() + ", enabled=" + enabled + ", role='" + role + '\'' + "]";
    }
}
