package com.saibende.saibende.model;




import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column(name="Txid",nullable = false)
    private String txid;

    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false )
    private ProductModel product;

    
    @Column(name = "store_name", nullable = false)
    private String store;

    @Column(nullable = false)
    private Double sales;

    @Column(nullable = false)
    private Double commission;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime addedAt;

    @Column(nullable = false)
    private LocalDateTime lastUpdated;

    @Column(nullable = false)
    private String pid; 

    
    @Column(nullable = false)
    private String affid1;
   


}
