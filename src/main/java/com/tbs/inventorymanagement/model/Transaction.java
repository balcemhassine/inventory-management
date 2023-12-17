package com.tbs.inventorymanagement.model;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private TransactionType transactionType;
    private int quantity;
    private LocalDateTime date;
    private Product product;


    public Transaction(int transactionId, TransactionType transactionType, int quantity, LocalDateTime date, Product product) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
        this.product = product;
    }

    public Transaction() {
    }

    public int getTransactionId() {
        return this.transactionId;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
