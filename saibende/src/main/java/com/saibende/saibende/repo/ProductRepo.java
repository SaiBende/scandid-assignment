package com.saibende.saibende.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saibende.saibende.model.ProductModel;

@Repository
public interface ProductRepo extends JpaRepository<ProductModel, Integer> {

    
}
