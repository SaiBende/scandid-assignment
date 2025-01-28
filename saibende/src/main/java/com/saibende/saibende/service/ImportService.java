// package com.saibende.saibende.service;

// import java.io.BufferedReader;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.io.Reader;
// import java.time.LocalDateTime;
// import java.util.Collections;
// import java.util.Iterator;
// import java.util.List;

// import org.apache.poi.ss.usermodel.Sheet;
// import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.ss.usermodel.Workbook;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import com.opencsv.CSVReader;
// import com.saibende.saibende.model.CategoryModel;
// import com.saibende.saibende.model.FlatDataDTO;
// import com.saibende.saibende.model.ProductModel;
// import com.saibende.saibende.model.TransactionModel;
// import com.saibende.saibende.repo.CategoryRepository;
// import com.saibende.saibende.repo.ProductRepository;
// import com.saibende.saibende.repo.TransactionRepository;

// import jakarta.transaction.Transactional;
// import lombok.AllArgsConstructor;


// @Service
// @AllArgsConstructor

// public class ImportService {

//     private final CategoryRepository categoryRepository;
//     private final ProductRepository productRepository;
//     private final TransactionRepository transactionRepository;

//     // public ImportService(CategoryRepository categoryRepository, ProductRepository productRepository, TransactionRepository transactionRepository) {
//     //     this.categoryRepository = categoryRepository;
//     //     this.productRepository = productRepository;
//     //     this.transactionRepository = transactionRepository;
//     // }

//     @Transactional
//     public void importXlsxData(MultipartFile file) {
//         try (InputStream is = file.getInputStream()) {
//             Workbook workbook = new XSSFWorkbook(is); // Open the XLSX file
//             Sheet sheet = workbook.getSheetAt(0);  // Get the first sheet

//             Iterator<Row> rowIterator = sheet.iterator();
//             // Skip the header row
//             if (rowIterator.hasNext()) rowIterator.next();

//             while (rowIterator.hasNext()) {
//                 Row row = rowIterator.next();
//                 FlatDataDTO flatData = mapRowToDto(row);
//                 importFlatData(Collections.singletonList(flatData));
//             }

//             workbook.close();
//         } catch (Exception e) {
//             throw new RuntimeException("Error parsing XLSX file: " + e.getMessage(), e);
//         }
       
//     }

//     private FlatDataDTO mapRowToDto(Row row) {
//         FlatDataDTO dto = new FlatDataDTO();

      
//         dto.setTxid(row.getCell(0).getStringCellValue());
//         dto.setStore(row.getCell(1).getStringCellValue());
//         dto.setProductid(row.getCell(2).getStringCellValue());
//         dto.setTitle(row.getCell(3).getStringCellValue());
//         dto.setCategoryid((int) row.getCell(4).getNumericCellValue());
//         dto.setCategoryname(row.getCell(5).getStringCellValue());
//         dto.setSales(row.getCell(6).getNumericCellValue());
//         dto.setPrice(row.getCell(7).getNumericCellValue());
//         dto.setCommission(row.getCell(8).getNumericCellValue());

//         dto.setOrderDate(row.getCell(9).getDateCellValue().toInstant()
//                 .atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
//         dto.setPid(row.getCell(10).getStringCellValue());
//         dto.setAffid1(row.getCell(11).getStringCellValue());
//         dto.setStatus(row.getCell(12).getStringCellValue());
//         dto.setAddedAt(row.getCell(13).getDateCellValue().toInstant()
//                 .atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
//         dto.setLastUpdated(row.getCell(14).getDateCellValue().toInstant()
//                 .atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());

//         return dto;
//     }

//     @Transactional
//     public void importFlatData(List<FlatDataDTO> flatDataList) {
//         for (FlatDataDTO flatData : flatDataList) {
//             // Check if category exists
//             CategoryModel category = categoryRepository.findById(flatData.getCategoryid()).orElse(null);
//             if (category == null) {
//                 category = new CategoryModel();
//                 category.setCategoryId(flatData.getCategoryid());
//                 category.setCategoryName(flatData.getCategoryname());
//                 category = categoryRepository.save(category);
//             }

//             // Check if product exists
//             ProductModel product = productRepository.findByProductId(flatData.getProductid());
//             if (product == null) {
//                 product = new ProductModel();
//                 product.setProductId(flatData.getProductid());
//                 product.setTitle(flatData.getTitle());
//                 product.setPrice(flatData.getPrice());
//                 product.setCategory(category);
//                 product = productRepository.save(product);
//             }

//             // Create and save transaction
//             TransactionModel transaction = new TransactionModel();
//             transaction.setTxid(flatData.getTxid());
//             transaction.setStore(flatData.getStore());
//             transaction.setProduct(product);
//             transaction.setSales(flatData.getSales());
//             transaction.setCommission(flatData.getCommission());
//             transaction.setOrderDate(flatData.getOrderDate());
//             transaction.setPid(flatData.getPid());
//             transaction.setAffid1(flatData.getAffid1());
//             transaction.setStatus(flatData.getStatus());
//             transaction.setAddedAt(flatData.getAddedAt());
//             transaction.setLastUpdated(flatData.getLastUpdated());

//             transactionRepository.save(transaction);
//         }
    

  
//         for (FlatDataDTO flatData : flatDataList) {
//             // Check if category exists
//             CategoryModel category = categoryRepository.findById(flatData.getCategoryid()).orElse(null);
//                     if(category == null) {
//                         category = new CategoryModel();
//                         category.setCategoryId(flatData.getCategoryid());
//                         category.setCategoryName(flatData.getCategoryname());
//                         category= categoryRepository.save(category);
//                     }

//             // Check if product exists
//             ProductModel product = productRepository.findByProductId(flatData.getProductid());
                    
//                     if(product == null) {
//                         product = new ProductModel();
//                         product.setProductId(flatData.getProductid());
//                         product.setTitle(flatData.getTitle());
//                         product.setPrice(flatData.getPrice());
//                         product.setCategory(category);
//                         product= productRepository.save(product);
//                     };

//             // Create and save transaction
//             TransactionModel transaction = new TransactionModel();
//             transaction.setTxid(flatData.getTxid());
//             transaction.setStore(flatData.getStore());
//             transaction.setProduct(product);
//             transaction.setSales(flatData.getSales());
//             transaction.setCommission(flatData.getCommission());
//             transaction.setOrderDate(flatData.getOrderDate());
//             transaction.setPid(flatData.getPid());
//             transaction.setAffid1(flatData.getAffid1());
//             transaction.setStatus(flatData.getStatus());
//             transaction.setAddedAt(flatData.getAddedAt());
//             transaction.setLastUpdated(flatData.getLastUpdated());

//             transactionRepository.save(transaction);
//         }


//     }
// }


package com.saibende.saibende.service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.saibende.saibende.model.CategoryModel;
import com.saibende.saibende.model.FlatDataDTO;
import com.saibende.saibende.model.ProductModel;
import com.saibende.saibende.model.TransactionModel;
import com.saibende.saibende.repo.CategoryRepository;
import com.saibende.saibende.repo.ProductRepository;
import com.saibende.saibende.repo.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImportService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public void importXlsxData(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip header row
            if (rowIterator.hasNext()) rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                FlatDataDTO flatData = mapRowToDto(row);
                importFlatData(Collections.singletonList(flatData));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error parsing XLSX file: " + e.getMessage(), e);
        }
    }

    private FlatDataDTO mapRowToDto(Row row) {
        FlatDataDTO dto = new FlatDataDTO();

        // Use CREATE_NULL_AS_BLANK to handle missing cells
        dto.setTxid(getStringValue(row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
        dto.setStore(getStringValue(row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
        dto.setProductid(getStringValue(row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
        dto.setTitle(getStringValue(row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));

        dto.setCategoryid((int) getNumericValue(row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
        dto.setCategoryname(getStringValue(row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));

        dto.setSales(getNumericValue(row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
        dto.setPrice(getNumericValue(row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
        dto.setCommission(getNumericValue(row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));

        dto.setOrderDate(getDateTimeValue(row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
        dto.setPid(getStringValue(row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
        dto.setAffid1(getStringValue(row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
        dto.setStatus(getStringValue(row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
        dto.setAddedAt(getDateTimeValue(row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
        dto.setLastUpdated(getDateTimeValue(row.getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));

        return dto;
    }

    private String getStringValue(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            // Convert numeric to string without decimal if integer
            double num = cell.getNumericCellValue();
            if (num == (int) num) {
                return String.valueOf((int) num);
            } else {
                return String.valueOf(num);
            }
        } else if (cell.getCellType() == CellType.BLANK) {
            return "";
        } else {
            throw new RuntimeException("Unexpected cell type for string: " + cell.getCellType());
        }
    }

    private double getNumericValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid numeric value: " + cell.getStringCellValue(), e);
            }
        } else if (cell.getCellType() == CellType.BLANK) {
            return 0.0;
        } else {
            throw new RuntimeException("Unexpected cell type for numeric: " + cell.getCellType());
        }
    }

    private LocalDateTime getDateTimeValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getDateCellValue().toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
        } else if (cell.getCellType() == CellType.STRING) {
            String dateStr = cell.getStringCellValue().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Adjust pattern as needed
            return LocalDateTime.parse(dateStr, formatter);
        } else {
            throw new RuntimeException("Unexpected cell type for date: " + cell.getCellType());
        }
    }

    @Transactional
    public void importFlatData(List<FlatDataDTO> flatDataList) {
        for (FlatDataDTO flatData : flatDataList) {
            // Check if category exists
            CategoryModel category = categoryRepository.findById(flatData.getCategoryid()).orElse(null);
            if (category == null) {
                category = new CategoryModel();
                category.setCategoryId(flatData.getCategoryid());
                category.setCategoryName(flatData.getCategoryname());
                category = categoryRepository.save(category);
            }

            // Check if product exists
            ProductModel product = productRepository.findByProductId(flatData.getProductid());
            if (product == null) {
                // product = new ProductModel();
                // product.setProductId(flatData.getProductid());
                // product.setTitle(flatData.getTitle());
                // product.setPrice(flatData.getPrice());
                // product.setCategory(category);
                // product = productRepository.save(product);


                product = new ProductModel();
                product.setProductId(flatData.getProductid());
    
                // Truncate product name if it exceeds the maximum length
                String productName = flatData.getTitle();
                int maxProductNameLength = 255; // Adjust this value based on your database column length
                if (productName.length() > maxProductNameLength) {
                    productName = productName.substring(0, maxProductNameLength);
                }
                product.setTitle(productName);
    
                product.setPrice(flatData.getPrice());
                product.setCategory(category);
                product = productRepository.save(product);
            }

            // Create and save transaction
            TransactionModel transaction = new TransactionModel();
            transaction.setTxid(flatData.getTxid());
            transaction.setStore(flatData.getStore());
            transaction.setProduct(product);
            transaction.setSales(flatData.getSales());
            transaction.setCommission(flatData.getCommission());
            transaction.setOrderDate(flatData.getOrderDate());
            transaction.setPid(flatData.getPid());
            transaction.setAffid1(flatData.getAffid1());
            transaction.setStatus(flatData.getStatus());
            transaction.setAddedAt(flatData.getAddedAt());
            transaction.setLastUpdated(flatData.getLastUpdated());

            transactionRepository.save(transaction);
        }
    }
}