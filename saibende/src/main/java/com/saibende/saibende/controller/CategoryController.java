package com.saibende.saibende.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saibende.saibende.model.CategoryModel;

import com.saibende.saibende.service.CategoryService;

@RestController
@RequestMapping("/api/category")
@CrossOrigin


public class CategoryController {
   @Autowired
   private CategoryService service;
    

    @GetMapping("/allcategories")
    public ResponseEntity<List<CategoryModel>> getCategory() {
        try {
            List<CategoryModel> categories = service.getAllCategories();
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
}
