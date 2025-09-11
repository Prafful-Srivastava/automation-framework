package com.example.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    //Initialize report
    public static ExtentReports createInstance(String reportPath){
      ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
      reporter.config().setDocumentTitle("Test Executiion Report");
      reporter.config().setReportName("Automation Test Report");

      extent = new ExtentReports();
      extent.attachReporter(reporter);

      return extent;
    }

    // Get ExtentReports instance
    public static ExtentReports getExtent() {
        return extent;
    }

    //Initializes a new test in the report.
    public static void startTest(String testName){
      ExtentTest test = extent.createTest(testName);
      extentTest.set(test); //stores the test in a ThreadLocal variable.
    }

    //Retrieves the current test instance for the current thread.
    public static ExtentTest getTest(){
      return extentTest.get();
    }

    //Cleans up the ThreadLocal variable after the test is done.
    public static void endTest(){
      extentTest.remove();
    }
}
