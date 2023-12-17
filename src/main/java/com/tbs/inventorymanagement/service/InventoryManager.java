package com.tbs.inventorymanagement.service;

import com.tbs.inventorymanagement.entity.ProductEntity;
import com.tbs.inventorymanagement.mapper.ProductMapper;
import com.tbs.inventorymanagement.model.Product;
import com.tbs.inventorymanagement.model.ReportObj;
import com.tbs.inventorymanagement.model.Transaction;
import com.tbs.inventorymanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
@Service
public class InventoryManager {
 //private List<Product> products = new ArrayList<>() ;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

 public List<Product> getAll(){
     return productMapper.entityToModel(productRepository.findAll());
 }

 public Product getProduct(int productID){
  ProductEntity productResult = productRepository.findProductByProductId(productID);
  if (productResult == null){
   throw new NoSuchElementException(String.format("The id %d is not found", productID));
  }
  return productMapper.entityToModel(productResult);
 }
    public List<Product> addProducts (List<Product> products){
        return productMapper.entityToModel(productRepository.saveAll(productMapper.modelToEntity(products)));
    }
 public Product addProduct(Product product){
   ProductEntity productToBeSaved = productMapper.modelToEntity(product);
   ProductEntity save = productRepository.save(productToBeSaved);
   System.out.println(String.format("The product %s is added successfully",productToBeSaved));
   return productMapper.entityToModel(save);
 }

 public List<Product> updateProduct(int productToBeUpdated,Product product){
   return null;
 }

 public boolean removeProduct(int idProductToBeRemoved){
    productRepository.deleteById(idProductToBeRemoved);
    return true;
 }

}
