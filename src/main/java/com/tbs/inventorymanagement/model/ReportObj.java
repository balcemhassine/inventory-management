package com.tbs.inventorymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportObj {

    private double totalRevenue;
    private Map<String, Double> revenueByCategory;
    private Map<String, Double> revenueByProduct;
    private String mostProfitableProduct;
    private String bestSellingProduct;
    private List<String> bestSellingProducts;
}
