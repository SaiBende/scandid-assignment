package com.saibende.saibende.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saibende.saibende.model.CategoryModel;


@Repository

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer>{

    //CategoryModel findByCategoryName(String );

    CategoryModel findById(int categoryId);
    
}
    

