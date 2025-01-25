package com.saibende.saibende.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key
    private int product_id;

    @Column(nullable = false)
    private String product_name;

    private String product_description;
    private String brand;
    private double product_price;
    private int quantity;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) // Foreign key to CategoryModel
    @JsonBackReference
    private CategoryModel category;

    private boolean available;

    private String product_image;

}
