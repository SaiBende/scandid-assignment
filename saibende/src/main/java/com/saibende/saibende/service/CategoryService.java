package com.saibende.saibende.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saibende.saibende.model.CategoryModel;
import com.saibende.saibende.repo.CategoryRepo;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo repo;

   public List<CategoryModel> getAllCategories() {
        return repo.findAll();
    }

    
    
}
