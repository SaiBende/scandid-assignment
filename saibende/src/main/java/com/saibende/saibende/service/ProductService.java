package com.saibende.saibende.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saibende.saibende.model.ProductModel;
import com.saibende.saibende.repo.ProductRepo;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;
    public List<ProductModel> getAllProducts() {
            return repo.findAll();
    }
    
    public ProductModel getProductById(int product_id) {
        return repo.findById(product_id).orElse(null);
    }
    
    public ProductModel addNewProduct(ProductModel product) {
        return repo.save(product);
    }
    
    public String updateProduct(int id) {
        return "Product with id: " + id + " updated successfully";
    }
    
    public String deleteProduct(int id) {
        return "Product with id: " + id + " deleted successfully";
    }
    
    public String getProductByCategory(String category) {
        return "List of products in category: " + category;
    }
    
    public String getProductByBrand(String brand) {
        return "List of products by brand: " + brand;
    }
    
    public String getProductByPrice(double price) {
        return "List of products by price: " + price;
    }
    
    public String getProductByAvailability(boolean available) {
        return "List of products by availability: " + available;
    }
    
    public String getProductByDate(String date) {
        return "List of products by date: " + date;
    }
    
    public String getProductByQuantity(int quantity) {
        return "List of products by quantity: " + quantity;
    }
    
    public String getProductByName(String name) {
        return "List of products by name: " + name;
    }
    
    public String getProductByDescription(String description) {
        return "List of products by description: " + description;
    }
    
    public String getProductByCategoryAndBrand(String category, String brand) {
        return "List of products by category: " + category + " and brand: " + brand;
    }
    
    public String getProductByCategoryAndPrice(String category, double price) {
        return "List of products by category: " + category + " and price: " + price;
    }
    
    public String getProductByCategoryAndAvailability(String category, boolean available) {
        return "List of products by category: " + category + " and availability: " + available;
    }
    
    public String getProductByCategoryAndDate(String category, String date) {
        return "List of products by category: " + category + " and date: " + date;
    }
    
    public String getProductByCategoryAndQuantity(String category, int quantity) {
        return "List of products by category"
                + category + " and quantity: " + quantity;  
    }

    
}
