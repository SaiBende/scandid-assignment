package com.saibende.saibende.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saibende.saibende.service.CSVService;

@RestController
@RequestMapping("/api/csv")
@CrossOrigin
public class CsvImportController {

    
    @Autowired
private CSVService csvService;



    @PostMapping("/import")
    public ResponseEntity<String> importCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty!");
        }

        try {
            // Save file temporarily
            String tempFilePath = System.getProperty("java.io.tmpdir") + file.getOriginalFilename();
            file.transferTo(new File(tempFilePath));

            // Process the CSV file
          
            csvService.importCombinedData(tempFilePath);

            return ResponseEntity.ok("CSV imported successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing CSV: " + ex.getMessage());
        }
    }

}
