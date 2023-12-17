package com.tbs.inventorymanagement.mapper;

import com.tbs.inventorymanagement.entity.ProductEntity;
import com.tbs.inventorymanagement.entity.TransactionEntity;
import com.tbs.inventorymanagement.entity.UserEntity;
import com.tbs.inventorymanagement.model.Product;
import com.tbs.inventorymanagement.model.Transaction;
import com.tbs.inventorymanagement.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductEntity modelToEntity(Product p);
    Product entityToModel(ProductEntity e);
    List<Product> entityToModel(List<ProductEntity> e);
    List<ProductEntity> modelToEntity (List<Product> e);

}
