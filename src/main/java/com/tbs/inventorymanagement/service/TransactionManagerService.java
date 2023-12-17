package com.tbs.inventorymanagement.service;

import com.tbs.inventorymanagement.entity.TransactionEntity;
import com.tbs.inventorymanagement.mapper.TransactionMapper;
import com.tbs.inventorymanagement.model.Transaction;
import com.tbs.inventorymanagement.repository.ProductRepository;
import com.tbs.inventorymanagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
    public class TransactionManagerService {

        //private static List<Transaction> transactions = new ArrayList<>() ;
        @Autowired
        private TransactionRepository transactionRepository;
        @Autowired
        private TransactionMapper transactionMapper;
        @Autowired
        private ProductRepository productRepository;
        public List<Transaction> getAll(){

            return transactionMapper.entityToModel(transactionRepository.findAll());
        }

        public Transaction getTransaction(int transactionID){
            Transaction transactionResult = transactionMapper.entityToModel(transactionRepository.findTransactionByTransactionId(transactionID));
            if (transactionResult== null){
                throw new NoSuchElementException(String.format("The id %d is not found", transactionID));
            }
            return transactionResult;
        }
        public List<Transaction> addTransactions (List<Transaction> transactions){
            List<TransactionEntity> entities = transactionMapper.modelToEntity(transactions);
            List<TransactionEntity> entityToAdd = entities.stream().map(transactionEntity -> {
               transactionEntity.setProduct(productRepository.findProductByProductId(transactionEntity.getProduct().getProductId()));
            return transactionEntity;
            }).collect(Collectors.toList());

            return transactionMapper.entityToModel(transactionRepository.saveAll(entityToAdd));
        }
        public Transaction addTransaction (Transaction transaction){
            TransactionEntity transactionToBeSaved = transactionMapper.modelToEntity(transaction);
            TransactionEntity save = transactionRepository.save(transactionToBeSaved);
            System.out.println(String.format("The transaction %s is added successfully",transactionToBeSaved));
            return transactionMapper.entityToModel(save);
        }

        public List<Transaction> updateTransaction(int transactionToBeUpdated,Transaction transaction){
            return null;
        }

        public boolean removeTransaction(int idTransactionToBeRemoved){
            transactionRepository.deleteById(idTransactionToBeRemoved);
            return true;
        }


    }
