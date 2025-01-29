package com.saibende.saibende.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saibende.saibende.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {

    ProductModel findByProductId(String productId);

    
}
