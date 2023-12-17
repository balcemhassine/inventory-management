package com.tbs.inventorymanagement.controler;

import com.tbs.inventorymanagement.entity.TransactionEntity;
import com.tbs.inventorymanagement.model.Transaction;
import com.tbs.inventorymanagement.service.TransactionManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/transactions")
@Controller
public class TransactionController {
    @Autowired
    private TransactionManagerService transactionManager;

    @GetMapping("/")
    public @ResponseBody List<Transaction> getTransaction() {
        return transactionManager.getAll();
    }

    @PostMapping("/list")
    public @ResponseBody List<Transaction> addTransactions(@RequestBody List<Transaction> newTransaction){
        return transactionManager.addTransactions(newTransaction);
    }

    @PostMapping("/")
    public @ResponseBody Transaction addTransaction(@RequestBody Transaction newTransaction){
        return transactionManager.addTransaction(newTransaction);
    }

    @DeleteMapping("/")
    public @ResponseBody boolean deleteTransaction(int idTransactionToBeDeleted){
        return transactionManager.removeTransaction(idTransactionToBeDeleted);
    }


}