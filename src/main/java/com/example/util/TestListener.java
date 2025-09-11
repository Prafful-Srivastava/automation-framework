package com.example.util;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener{
  
  @Override
  public void onTestStart(ITestResult result){
    //System.out.println("Test started: " + result.getName());
     ExtentReportManager.startTest(result.getName());
  }

  @Override
  public void onTestSuccess(ITestResult result){
    ExtentReportManager.getTest().pass("Test passed");
    //System.out.println("Test success: " + result.getName());
  }

  @Override
  public void onTestFailure(ITestResult result){
    WebDriver driver = WebDriverManagerUtil.getDriver();
    String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
    ExtentReportManager.getTest().fail(result.getThrowable())
                         .addScreenCaptureFromPath(screenshotPath);
    //System.out.println("Screenshot saved at: " + screenshotPath);
    //System.out.println("Test failed: " + result.getName());
  }

  @Override
  public void onTestSkipped(ITestResult result){
    System.out.println("Test skipped: " + result.getName());
  }

}
