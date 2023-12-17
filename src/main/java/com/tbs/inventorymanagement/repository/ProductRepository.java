package com.tbs.inventorymanagement.repository;

import com.tbs.inventorymanagement.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    public ProductEntity findProductByProductId(Integer id);
}
