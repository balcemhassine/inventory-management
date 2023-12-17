package com.tbs.inventorymanagement.model;


public class Product {
    private Integer productId;
    private String productName;
    private Integer quantityInStock;
    private Double price;
    private String category;

    // Constructor
    public Product(Integer productId, String productName, Integer quantityInStock, Double price, String category) {
        this.productId = productId;
        this.productName = productName;
        this.quantityInStock = quantityInStock;
        this.price = price;
        this.category = category;
    }

    public Product() {
    }

    public Integer getProductId() {
        return this.productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public Integer getQuantityInStock() {
        return this.quantityInStock;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getCategory() {
        return this.category;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

