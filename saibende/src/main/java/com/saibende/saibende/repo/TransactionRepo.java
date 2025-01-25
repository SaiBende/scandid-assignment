package com.saibende.saibende.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saibende.saibende.model.TransactionModel;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionModel, Integer>{
    
}
