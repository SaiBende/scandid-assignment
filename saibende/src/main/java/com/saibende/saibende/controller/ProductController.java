package com.saibende.saibende.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saibende.saibende.model.ProductModel;
import com.saibende.saibende.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin


public class ProductController {
    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet() {
        return "Welcome to Saibende!";
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getProducts() {
        try {
            List<ProductModel> products = service.getAllProducts();
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // return service.getAllProducts();
    }

   

}
