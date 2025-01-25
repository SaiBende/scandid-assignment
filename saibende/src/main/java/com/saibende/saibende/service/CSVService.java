package com.saibende.saibende.service;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.saibende.saibende.model.CategoryModel;
import com.saibende.saibende.model.ProductModel;
import com.saibende.saibende.model.TransactionModel;
import com.saibende.saibende.repo.CategoryRepo;
import com.saibende.saibende.repo.ProductRepo;
import com.saibende.saibende.repo.TransactionRepo;

import lombok.NoArgsConstructor;



@NoArgsConstructor
@Service

public class CSVService {
    @Autowired
    private CategoryRepo categoryRepository;

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private TransactionRepo transactionRepository;

    /**
     * Import CSV data into the database.
     * 
     * @param filePath Path to the CSV file.
     * @throws IOException If an I/O error occurs.
     * @throws CsvValidationException If the CSV is invalid.
     */
    
     public void importCombinedData(String filePath) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] data;
            boolean isHeader = true;
    
            while ((data = reader.readNext()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
    
                try {
                    // Extract and save Category
                    String categoryName = data[6];
                    String categoryDescription = data[7];
                    CategoryModel category = categoryRepository.findByCategoryName(categoryName);
                    if (category == null) {
                        category = new CategoryModel();
                        category.setCategoryName(categoryName);
                        category.setCategory_description(categoryDescription);
                        category = categoryRepository.save(category);
                    }
    
                    // Extract and save Product
                    String productName = data[0];
                    ProductModel product = new ProductModel();
                    product.setProduct_name(productName);
                    product.setProduct_description(data[1]);
                    product.setBrand(data[2]);
                    product.setProduct_price(Double.parseDouble(data[3]));
                    product.setQuantity(Integer.parseInt(data[4]));
                    product.setTransactionDate(java.sql.Date.valueOf(data[5]));
                    product.setCategory(category);
                    product.setAvailable(true);
                    product.setProduct_image(data[9]);
                    product = productRepository.save(product);
    
                    // Extract and save Transaction
                    TransactionModel transaction = new TransactionModel();
                    transaction.setProduct(product); // Set the product object
                    transaction.setQuantity(Integer.parseInt(data[8]));
                    transaction.setTransactionDate(java.sql.Date.valueOf(data[5]));
                    transactionRepository.save(transaction);
    
                } catch (Exception ex) {
                    System.err.println("Error processing row: " + String.join(",", data));
                    ex.printStackTrace();
                }
            }
        }
    }
    

    

}
