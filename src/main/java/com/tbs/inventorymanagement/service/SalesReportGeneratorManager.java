package com.tbs.inventorymanagement.service;


import com.lowagie.text.DocumentException;
import com.tbs.inventorymanagement.entity.ProductEntity;
import com.tbs.inventorymanagement.entity.TransactionEntity;
import com.tbs.inventorymanagement.mapper.ProductMapper;
import com.tbs.inventorymanagement.mapper.TransactionMapper;
import com.tbs.inventorymanagement.model.ReportObj;
import com.tbs.inventorymanagement.repository.TransactionRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalesReportGeneratorManager {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CsvAndPdfFileService csvAndPdfFileService;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private ProductMapper productMapper;

    public void generateTransactionsCsvFile(String path) throws IOException {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        csvAndPdfFileService
                .transactionsListToCsvFile(
                        transactionMapper.entityToModel(transactions),
                        path);
    }
    public ReportObj generateFullReport(){
        ReportObj reportObj = new ReportObj();
        reportObj.setBestSellingProduct(findBestSellingProduct());
        reportObj.setTotalRevenue(calculateTotalSalesRevenue());
        reportObj.setMostProfitableProduct(findMostProfitableProduct());
        reportObj.setRevenueByCategory(calculateSalesRevenueByCategory());
        reportObj.setRevenueByProduct(calculateSalesRevenueByProduct());
        reportObj.setBestSellingProducts(
                productMapper.entityToModel(
                        getBestSellingProductsOrdered()
                ).stream().map(each -> each.getProductName()).collect(Collectors.toList())
        );
        return reportObj;
    }

    public void generatePdfReportFile(String path) throws IOException, DocumentException {
       csvAndPdfFileService.generatePdfFromHtml(csvAndPdfFileService.parseThymeleafTemplate(generateFullReport()), path);
    }

    public List<TransactionEntity> generateSalesReport(LocalDateTime startDate, LocalDateTime endDate) {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        System.out.println("Sales Report for the Date Range: " + startDate + " to " + endDate);
        return transactions.stream().filter(tr -> tr.getDate().isAfter(startDate) && tr.getDate().isBefore(endDate)).collect(Collectors.toList());
    }

    public List<TransactionEntity> generateSalesReportByProductId(int productId) {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        System.out.println("Sales Report for Product ID: " + productId);
        return transactions.stream().filter(tr -> tr.getProduct().getProductId() == productId).collect(Collectors.toList());

    }

    // Method to calculate total sales revenues for SALE transactions
    public double calculateTotalSalesRevenue() {
       // List<TransactionEntity> ListOfTransactionEntity = entityManager.createQuery("SELECT e FROM transactions e").getResultList();

        List<TransactionEntity> transactions = transactionRepository.findAllTransactionEntities().stream().collect(Collectors.toList());
        double totalRevenue = 0.0;

        // Iterate through SALE transactions and accumulate revenue
        for (TransactionEntity transaction : transactions) {
            if ("SALE".equals(transaction.getTransactionType())) {
                totalRevenue += transaction.getQuantity() * transaction.getProduct().getPrice();
            }
        }
        return totalRevenue;
    }

    // Method to calculate sales revenues by category for SALE transactions
    public Map<String, Double> calculateSalesRevenueByCategory() {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        Map<String, Double> revenueByCategory = new HashMap<>();

        // Iterate through SALE transactions and accumulate revenue by category
        for (TransactionEntity transaction : transactions) {
            if ("SALE".equals(transaction.getTransactionType())) {
                String category = transaction.getProduct().getCategory();
                double revenue = transaction.getQuantity() * transaction.getProduct().getPrice();

                revenueByCategory.put(category, revenue + revenueByCategory.getOrDefault(category, 0.0));
            }
        }
        return revenueByCategory;
    }



    // Method to calculate sales revenues by product for SALE transactions
    public Map<String, Double> calculateSalesRevenueByProduct() {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        Map<String, Double> revenueByProduct = new HashMap<>();

        // Iterate through SALE transactions and accumulate revenue by product
        for (TransactionEntity transaction : transactions) {
            if ("SALE".equals(transaction.getTransactionType())) {
                String productName = transaction.getProduct().getProductName();
                double revenue = transaction.getQuantity() * transaction.getProduct().getPrice();

                revenueByProduct.put(productName, revenue + revenueByProduct.getOrDefault(productName, 0.0));
            }
        }

        return revenueByProduct;
    }

    // Method to determine most profitable product for SALE transactions
    public String findMostProfitableProduct() {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        Map <String, Double> revenueByProduct = calculateSalesRevenueByProduct();
        double maxRevenue = 0.0;
        String mostProfitableProduct = null;

        // Iterate through products and find the best
        for (Map.Entry<String, Double> entry : revenueByProduct.entrySet()) {
            if (entry.getValue() > maxRevenue) {
                maxRevenue = entry.getValue();
                mostProfitableProduct = entry.getKey();
            }
        }

        return mostProfitableProduct;
    }

    public String findBestSellingProduct() {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        Map <String, Integer> quantityByProduct = new HashMap<>();
        for (TransactionEntity transaction : transactions) {
            if ("SALE".equals(transaction.getTransactionType())) {
                String product = transaction.getProduct().getProductName();
                int quantitySold = transaction.getQuantity();

                quantityByProduct.put(product, quantitySold + quantityByProduct.getOrDefault(product, 0));
            } }
        int maxQuantity = 0;
        String bestSellingProduct = null;
        for (Map.Entry<String, Integer> entry : quantityByProduct.entrySet()) {
            if (entry.getValue() > maxQuantity) {
                maxQuantity = entry.getValue();
                bestSellingProduct = entry.getKey();
            }
        }
    return bestSellingProduct; }

    // Method to get best-selling products ordered by quantity sold
    public List<ProductEntity> getBestSellingProductsOrdered() {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        // Create a map to store the total quantity sold for each product
        Map<ProductEntity, Integer> quantitySoldByProduct = new HashMap<>();

        // Iterate through SALE transactions and accumulate quantity sold by product
        for (TransactionEntity transaction : transactions) {
            if ("SALE".equals(transaction.getTransactionType())) {
                ProductEntity product = transaction.getProduct();
                int quantitySold = transaction.getQuantity();

                quantitySoldByProduct.put(product, quantitySold + quantitySoldByProduct.getOrDefault(product, 0));
            }
        }

        // Sort the products by quantity sold in descending order
        List<ProductEntity> bestSellingProducts = quantitySoldByProduct.entrySet()
                .stream()
                .sorted(Map.Entry.<ProductEntity, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return bestSellingProducts;
    }

    // Method to display the current inventory status considering both PURCHASE and SALE transactions
    public Map<ProductEntity, Integer> displayInventoryStatus() {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        // Create a map to store the current quantity in stock for each product
        Map<ProductEntity, Integer> quantityInStockByProduct = new HashMap<>();

        // Process transactions to update quantity in stock based on transactionType
        for (TransactionEntity transaction : transactions) {
            ProductEntity product = transaction.getProduct();
            int quantity = transaction.getQuantity();

            if ("PURCHASE".equals(transaction.getTransactionType())) {
                quantityInStockByProduct.put(product, quantity + quantityInStockByProduct.getOrDefault(product, 0));
            } else if ("SALE".equals(transaction.getTransactionType())) {
                int currentQuantityInStock = quantityInStockByProduct.getOrDefault(product, 0);
                quantityInStockByProduct.put(product, currentQuantityInStock - quantity);
            }
        }
        return quantityInStockByProduct;
    }

}
