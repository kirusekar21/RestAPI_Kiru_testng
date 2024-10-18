package utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    static DataFormatter dataFormatter = new DataFormatter();

    public static List<LinkedHashMap<String, String>> getExcelDataAsListOfMap(String sheetName) {
        List<LinkedHashMap<String, String>> data = new ArrayList<>();
        
        // Use ClassLoader to get the resource
        try (InputStream fis = ExcelReader.class.getClassLoader().getResourceAsStream("TestData/ExcelRequests.xlsx");
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
             
            if (fis == null) {
                throw new FileNotFoundException("Excel file not found in resources");
            }
            
            XSSFSheet sheet = workbook.getSheet(sheetName); // Get the specified sheet
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }
            
            int totalRow = sheet.getPhysicalNumberOfRows();
            LinkedHashMap<String, String> mapData;
            List<String> allKeys = new ArrayList<>();
            
            for (int i = 0; i < totalRow; i++) {
                mapData = new LinkedHashMap<>();
                Row row = sheet.getRow(i); // Get the current row
                if (row == null) {
                    // If the row is null, skip it
                    continue;
                }
                
                int totalCols = row.getPhysicalNumberOfCells(); // Get number of cells
                if (i == 0) {
                    // Read headers
                    for (int j = 0; j < totalCols; j++) {
                        String header = dataFormatter.formatCellValue(row.getCell(j));
                        if (header != null && !header.isEmpty()) {
                            allKeys.add(header);
                        }
                    }
                } else {
                    // Populate mapData for data rows
                    for (int j = 0; j < totalCols; j++) {
                        // Ensure we do not exceed the allKeys size
                        if (j < allKeys.size()) {
                            String cellValue = dataFormatter.formatCellValue(row.getCell(j));
                            mapData.put(allKeys.get(j), cellValue != null ? cellValue : ""); // Set empty string if null
                        }
                    }
                    data.add(mapData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        
        return data; 
    }
}
//package utils;
//
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelReader {
//    static DataFormatter dataFormatter = new DataFormatter();
//
//    public static List<LinkedHashMap<String, String>> getExcelDataAsListOfMap(String sheetName) {
//        List<LinkedHashMap<String, String>> data = new ArrayList<>();
//        
//        // Use ClassLoader to get the resource
//        try (InputStream fis = ExcelReader.class.getClassLoader().getResourceAsStream("TestData/ExcelRequests.xlsx");
//             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
//             
//            if (fis == null) {
//                throw new FileNotFoundException("Excel file not found in resources");
//            }
//            
//            XSSFSheet sheet = workbook.getSheet(sheetName); // Get the specified sheet
//            if (sheet == null) {
//                throw new IllegalArgumentException("Sheet not found: " + sheetName);
//            }
//            
//            int totalRow = sheet.getPhysicalNumberOfRows();
//            LinkedHashMap<String, String> mapData;
//            List<String> allKeys = new ArrayList<>();
//            
//            for (int i = 0; i < totalRow; i++) {
//                mapData = new LinkedHashMap<>();
//                Row row = sheet.getRow(i); // Get the current row
//                if (row == null) {
//                    // If the row is null, skip it (optional based on your needs)
//                    continue;
//                }
//                
//                if (i == 0) {
//                    // Read headers
//                    int totalCols = row.getPhysicalNumberOfCells();
//                    for (int j = 0; j < totalCols; j++) {
//                        String header = dataFormatter.formatCellValue(row.getCell(j));
//                        if (header != null && !header.isEmpty()) {
//                            allKeys.add(header);
//                        }
//                    }
//                } else {
//                    int totalCols = row.getPhysicalNumberOfCells();
//                    for (int j = 0; j < totalCols; j++) {
//                        String cellValue = dataFormatter.formatCellValue(row.getCell(j));
//                        if (cellValue != null) {
//                            mapData.put(allKeys.get(j), cellValue);
//                        } else {
//                            // Handle the case of null value (optional)
//                            mapData.put(allKeys.get(j), ""); // Set empty string or some default value
//                        }
//                    }
//                    data.add(mapData);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace(); 
//        }
//        
//        return data; 
//    }
//}
