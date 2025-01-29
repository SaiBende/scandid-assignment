package com.saibende.saibende.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saibende.saibende.model.ProductModel;
import com.saibende.saibende.repo.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;
    
    public List<ProductModel> getAllProducts() {
            return repo.findAll();
    }
     
    public ProductModel getProductById(String productId) {
        return repo.findByProductId(productId);
    }
   
}
