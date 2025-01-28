package com.saibende.saibende.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor


public class CategoryModel {
    @Id
    @Column(name="category_id")
    private int categoryId;


    @Column(name = "category_name")
    private String categoryName;


}
