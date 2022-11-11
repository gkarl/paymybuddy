package com.payMyBuddy.pay.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DynamicUpdate
@Table(name = "user_contacts")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(
            fetch = FetchType.LAZY, // A la récupération de Contact, user n'est pas récupéré automatiquement, les performances sont meilleures (la requête est plus légère)
            cascade = CascadeType.DETACH
    )
    @JoinColumn(name = "user_contact_id")
    @JsonIgnoreProperties({"password", "balance", "account", "contactList"})
    private User userContact;

    @ManyToOne(
            fetch = FetchType.LAZY, // A la récupération de contact user n'est pas récupérés automatiquement, les performances sont meilleures (la requête est plus légère)
            cascade = CascadeType.DETACH
    )
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "balance", "account", "contactList"})
    private User user;

    public Contact() {}

    public Contact(Integer id, User userContact, User user) {
        this.id = id;
        this.userContact = userContact;
        this.user = user;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserContact() {
        return userContact;
    }

    public void setUserContact(User userContact) {
        this.userContact = userContact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contact {" + "id=" + getId() + "userContact=" + getUserContact() + "user=" + getUser() + "}" ;
    }
}
