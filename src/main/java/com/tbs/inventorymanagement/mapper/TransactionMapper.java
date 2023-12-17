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
public interface TransactionMapper {

    TransactionEntity modelToEntity(Transaction t);
    Transaction entityToModel(TransactionEntity b);
    List<Transaction> entityToModel(List<TransactionEntity> e);
    List<TransactionEntity> modelToEntity (List<Transaction> e);
}
