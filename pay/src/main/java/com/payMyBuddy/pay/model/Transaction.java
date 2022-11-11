package com.payMyBuddy.pay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


import java.time.LocalDate;
import java.util.Date;

@Entity
@DynamicUpdate
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "date")
    private LocalDate date;

    @NotBlank
    @Column(name = "amount")
    private  Double amountTransaction;

    @Column(name = "description")
    private String description;


    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "sender_user_id") // pour faire l'association avec la clé étrangére sender_user_id dans la table transaction
    private User senderUser;


    @OneToOne(
            fetch = FetchType.EAGER // Lorsqu'on va récupérer la transaction, l'user associés est récupéré
    )
    @JoinColumn(name = "recipient_user_id")  // pour faire l'association avec la clé étrangére recipient_user_id dans la table transaction
    @JsonIgnoreProperties({"id", "last_name", "email", "password", "balance", "account", "contactList"})
    private User recipientUser;


    public Transaction() {
    }

    public Transaction(Integer id, LocalDate date, Double amountTransaction, String description, User senderUser, User recipientUser) {
        this.id = id;
        this.date = date;
        this.amountTransaction = amountTransaction;
        this.description = description;
        this.senderUser = senderUser;
        this.recipientUser = recipientUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmountTransaction() {
        return amountTransaction;
    }

    public void setAmountTransaction(Double amountTransaction) {
        this.amountTransaction = amountTransaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public User getRecipientUser() {
        return recipientUser;
    }

    public void setRecipientUser(User recipientUser) {
        this.recipientUser = recipientUser;
    }
}
