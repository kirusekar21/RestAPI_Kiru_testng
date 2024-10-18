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
        
        
        try (InputStream fis = ExcelReader.class.getClassLoader().getResourceAsStream("TestData/ExcelRequests.xlsx");
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
             
            if (fis == null) {
                throw new FileNotFoundException("Excel file not found in resources");
            }
            
            XSSFSheet sheet = workbook.getSheet(sheetName); 
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }
            
            int totalRow = sheet.getPhysicalNumberOfRows();
            LinkedHashMap<String, String> mapData;
            List<String> allKeys = new ArrayList<>();
            
            for (int i = 0; i < totalRow; i++) {
                mapData = new LinkedHashMap<>();
                Row row = sheet.getRow(i); 
                if (row == null) {
                    
                    continue;
                }
                
                int totalCols = row.getPhysicalNumberOfCells(); 
                if (i == 0) {
                    // Read headers
                    for (int j = 0; j < totalCols; j++) {
                        String header = dataFormatter.formatCellValue(row.getCell(j));
                        if (header != null && !header.isEmpty()) {
                            allKeys.add(header);
                        }
                    }
                } else {
                    
                    for (int j = 0; j < totalCols; j++) {
                        
                        if (j < allKeys.size()) {
                            String cellValue = dataFormatter.formatCellValue(row.getCell(j));
                            mapData.put(allKeys.get(j), cellValue != null ? cellValue : ""); 
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
