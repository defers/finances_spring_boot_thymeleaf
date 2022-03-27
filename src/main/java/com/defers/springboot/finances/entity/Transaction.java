package com.defers.springboot.finances.entity;

import com.defers.springboot.finances.enums.TransactionType;

import javax.persistence.*;
import java.util.UUID;

//@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = Currency.class)
    private Currency currency;

    @OneToOne(targetEntity = Category.class)
    private Category category;

    @OneToOne(targetEntity = Account.class)
    private Account account;

    private TransactionType type;

    @Column(precision=10, scale=2, columnDefinition = "default 0")
    private double sum;

    public Transaction() {
    }

    public Transaction(Account account, Category category, double sum, TransactionType type) {
        this.account = account;
        this.category = category;
        this.currency = account.getCurrency();
        this.sum = sum;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Category{" +
                ", id=" + id +
                ", currency=" + currency +
                ", account=" + account +
                ", type=" + type +
                ", sum=" + sum +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
