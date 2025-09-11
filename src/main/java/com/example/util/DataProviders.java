package com.example.util;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        String filePath = "src/test/resources/testdata/TestData.xlsx";
        String sheetName = "Login";
        return ExcelUtils.getData(filePath, sheetName);
    }

    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() {
        String filePath = "src/test/resources/testdata/TestData.xlsx";
        String sheetName = "Registration";
        return ExcelUtils.getData(filePath, sheetName);
    }
}
