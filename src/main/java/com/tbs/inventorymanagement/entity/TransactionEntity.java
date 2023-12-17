package com.tbs.inventorymanagement.entity;

import com.tbs.inventorymanagement.model.Product;
import com.tbs.inventorymanagement.model.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    private Integer transactionId;
    private String transactionType;
    private int quantity;
    private LocalDateTime date;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "product_id")
    private ProductEntity product;


}
