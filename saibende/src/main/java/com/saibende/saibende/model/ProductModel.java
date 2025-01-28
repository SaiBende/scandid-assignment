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
@Table(name = "product")
public class ProductModel {

    @Id 
    private String productId;
    
    @Column(name = "product_name")
    private String title;

    @ManyToOne
    @JoinColumn(name ="category_name",nullable = false)

    private CategoryModel category;

    @Column(name = "product_price" , nullable = false)
    private Double price;

    

}
