package com.payMyBuddy.pay.model;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@DynamicUpdate
@Table(name = "movement")
public class Movement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "amount")
    private Double amount;

    @NotBlank
    @Column(name = "date")
    private LocalDate date;


    @OneToOne(
            fetch = FetchType.EAGER // Lorsqu'on va récupérer un Movement l'user associé est récupéré
    )
    @JoinColumn(name = "user_id") // pour faire l'association avec la clé étrangére user_id dans la table movement
    private User user;


    @OneToOne(
            fetch = FetchType.EAGER // Lorsqu'on va récupérer un Movement l'account associé est récupéré
    )
    @JoinColumn(name = "account_id") // pour faire l'association avec la clé étrangére account_id dans la table movement
    private Account account;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
