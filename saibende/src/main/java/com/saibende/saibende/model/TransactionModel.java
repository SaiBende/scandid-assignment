package com.saibende.saibende.model;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TransactionModel {
    
    

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int transaction_id;
    
        @ManyToOne // Establish a relationship with ProductModel
        @JoinColumn(name = "product_id", nullable = false) // Foreign key column
        private ProductModel product;
    
        private int quantity;
    
        @Column(name = "transaction_date")
        private Date transactionDate;
    
 
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private int transaction_id;

    // @ManyToOne
    // @JoinColumn(name = "product_id", nullable = false) // Foreign key to ProductModel
    // private ProductModel product;

    // private int quantity;

    // @Column(name = "transaction_date")
    // private Date transactionDate;

   


}
