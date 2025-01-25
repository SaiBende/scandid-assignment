package com.saibende.saibende.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saibende.saibende.model.TransactionModel;
import com.saibende.saibende.repo.TransactionRepo;


@Service
public class TransactionService {
 @Autowired
 private TransactionRepo repo;
 

 public List<TransactionModel>getAllTransaction(){
    return repo.findAll();

 }
 
    
}
