package com.saibende.saibende.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saibende.saibende.service.ImportService;

@RestController
@RequestMapping("/api/csv")
@CrossOrigin
public class XlsxImportController {

    
    @Autowired
    private ImportService csvService;


    @PostMapping("/import")
    public ResponseEntity<String> importXlsxData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty. Please upload a valid CSV file.");
        }

        try {
            csvService.importXlsxData(file);
            return ResponseEntity.ok("Data imported successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error importing data: " + e.getMessage());
        }
    }
}
