package com.payMyBuddy.pay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
//@DynamicUpdate
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank
    @Column(name = "iban")
    private String iban;

    @NotBlank
    @Column(name = "balance")
    private Double balance;

    @OneToOne(
            fetch = FetchType.LAZY, // A la récupération de Account, user n'est pas récupéré automatiquement, les performances sont meilleures (la requête est plus légère)
            cascade = {
                    CascadeType.PERSIST, // la cascade s’applique donc tant en création qu’en modification ->  nous ne voulons pas un cascade.ALL qui impliquerait la suppression
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "user_id") // pour faire l'association avec la clé étrangére user_id dans la table account
    @JsonIgnoreProperties({"password", "balance", "account", "contactList"})
    private User user;

    public Account() {}

    public Account(Integer id, String iban, Double balance, User user) {
        this.id = id;
        this.iban = iban;
        this.balance = balance;
        this.user = user;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
