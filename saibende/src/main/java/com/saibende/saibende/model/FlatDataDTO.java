package com.saibende.saibende.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FlatDataDTO {
    private String txid;
    private String store;
    private String productid;
    private String title;
    private Integer categoryid;
    private String categoryname;
    private double sales;
    private double price;
    private double commission;
    private LocalDateTime orderDate;
    private String pid;
    private String affid1;
    private String status;
    private LocalDateTime addedAt;
    private LocalDateTime lastUpdated;
}
