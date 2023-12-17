package com.tbs.inventorymanagement.model;

import com.tbs.inventorymanagement.entity.TransactionEntity;
import com.tbs.inventorymanagement.model.Product;
import com.tbs.inventorymanagement.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
public class SalesReportGenerator {
    private List<TransactionEntity> transactions;
    private Object salesData;




}
