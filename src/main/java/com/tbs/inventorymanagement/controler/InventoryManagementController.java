package com.tbs.inventorymanagement.controler;


import com.tbs.inventorymanagement.entity.ProductEntity;
import com.tbs.inventorymanagement.model.Transaction;
import com.tbs.inventorymanagement.service.InventoryManager;
import com.tbs.inventorymanagement.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/inventory")
@Controller
public class InventoryManagementController {
    @Autowired
    private InventoryManager inventoryManager;

    @GetMapping("/")
    public @ResponseBody List<Product> getInventory(){
        return inventoryManager.getAll();
    }

    @PostMapping("/list")
    public @ResponseBody List<Product> addProducts(@RequestBody List<Product> newTransaction){
        return inventoryManager.addProducts(newTransaction);
    }

    @PostMapping("/")
    public @ResponseBody Product addProductToInventory(@RequestBody Product newProduct){
        return inventoryManager.addProduct(newProduct);
    }

    @DeleteMapping("/")
    public @ResponseBody boolean deleteProductFromInventory(int idProductToBeDeleted){
        return inventoryManager.removeProduct(idProductToBeDeleted);
    }

}
