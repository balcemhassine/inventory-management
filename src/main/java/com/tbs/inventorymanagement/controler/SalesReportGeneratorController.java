package com.tbs.inventorymanagement.controler;



import com.lowagie.text.DocumentException;
import com.tbs.inventorymanagement.entity.ProductEntity;
import com.tbs.inventorymanagement.entity.TransactionEntity;
import com.tbs.inventorymanagement.mapper.ProductMapper;
import com.tbs.inventorymanagement.model.GenerateCsvRequest;
import com.tbs.inventorymanagement.model.ReportObj;
import com.tbs.inventorymanagement.service.SalesReportGeneratorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/reporting")
@Controller
public class SalesReportGeneratorController {

    @Autowired
    private SalesReportGeneratorManager salesReportGeneratorManager;

    @GetMapping("/SalesReportByDateRange/{startDate}@{endDate}")
    public @ResponseBody List<TransactionEntity> SalesReportByDateRange(@PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate) throws IOException {
        return salesReportGeneratorManager.generateSalesReport(startDate, endDate);
    }

    @GetMapping("/salesReportGeneratorByProduct/{productId}")
    public @ResponseBody List<TransactionEntity> salesReportGeneratorByProduct(@PathVariable int productId) {
        return salesReportGeneratorManager.generateSalesReportByProductId(productId);
    }


    @GetMapping("/totalRevenue")
    public @ResponseBody double totalSalesRevenue() {
        return salesReportGeneratorManager.calculateTotalSalesRevenue();
    }

    @GetMapping("/salesRevenueByCategory")
    public @ResponseBody Map<String, Double> salesRevenueByCategory() {
        return salesReportGeneratorManager.calculateSalesRevenueByCategory();
    }

    @GetMapping("/salesRevenueByProduct")
    public @ResponseBody Map<String, Double> salesRevenueByProduct() {
        return salesReportGeneratorManager.calculateSalesRevenueByProduct();
    }

    @GetMapping("/bestSellingProduct")
    public @ResponseBody String bestSellingProduct() {
        return salesReportGeneratorManager.findBestSellingProduct();
    }

    @GetMapping("/mostProfitableProduct")
    public @ResponseBody String mostProfitableProduct() {
        return salesReportGeneratorManager.findMostProfitableProduct();
    }

    @GetMapping("/bestSellingProductsOrdered")
    public @ResponseBody List<ProductEntity> bestSellingProductsOrdered() {
        return salesReportGeneratorManager.getBestSellingProductsOrdered();
    }

    @GetMapping("/inventoryStatus")
    public @ResponseBody Map<ProductEntity, Integer> inventoryStatus() {
        return salesReportGeneratorManager.displayInventoryStatus();
    }

    @PostMapping("/generateCsv")
    public @ResponseBody Boolean generateCsv(@RequestBody GenerateCsvRequest gCsvR) throws IOException, DocumentException {
        salesReportGeneratorManager.generateTransactionsCsvFile(gCsvR.getFilePath()+ ".csv");
        salesReportGeneratorManager.generatePdfReportFile(gCsvR.getFilePath() + ".pdf" );
        return true;
    }

    @GetMapping("/salesReport")
    public @ResponseBody ReportObj salesReport() {
        return salesReportGeneratorManager.generateFullReport();
    }

}
