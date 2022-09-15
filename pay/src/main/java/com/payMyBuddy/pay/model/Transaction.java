package com.payMyBuddy.pay.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


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
    private Date date;

    @NotBlank
    @Column(name = "amount")
    private  Double amount;

    @Column(name = "description")
    private String description;

    public Transaction() {
    }

    public Transaction(Integer id, Date date, Double amount, String description) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}