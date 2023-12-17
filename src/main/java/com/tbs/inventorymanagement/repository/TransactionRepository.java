package com.tbs.inventorymanagement.repository;

import com.tbs.inventorymanagement.entity.TransactionEntity;
import com.tbs.inventorymanagement.entity.UserEntity;
import com.tbs.inventorymanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    public TransactionEntity findTransactionByTransactionId(Integer id);

    @Query(
            value = "SELECT * FROM transactions",
            nativeQuery = true)
    Collection<TransactionEntity> findAllTransactionEntities();
}
