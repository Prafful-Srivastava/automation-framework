package com.example.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
  public static Object[][] getData(String filePath, String sheetName) {
    Object[][] data = null;

    try (FileInputStream fis = new FileInputStream(filePath);
         Workbook workbook = WorkbookFactory.create(fis)) {

        Sheet sheet = workbook.getSheet(sheetName);

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        data = new Object[rows - 1][cols]; // exclude header row

        for (int i = 1; i < rows; i++) { // start from row 1 (skip header)
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return data;
}

}

