package com.saibende.saibende.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saibende.saibende.model.CategoryModel;
import com.saibende.saibende.repo.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

   public List<CategoryModel> getAllCategories() {
        return repo.findAll();
    }

    
    
}
