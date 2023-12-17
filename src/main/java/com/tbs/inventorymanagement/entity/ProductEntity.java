package com.tbs.inventorymanagement.entity;


import com.tbs.inventorymanagement.model.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    private Integer productId;
    private String productName;
    private Integer quantityInStock;
    private Double price;
    private String category;

    //@OneToOne(mappedBy = "product", cascade= CascadeType.REMOVE)
    //private TransactionEntity transaction;


}

