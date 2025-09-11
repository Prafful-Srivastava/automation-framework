package com.example.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

  public static String captureScreenshot(WebDriver driver, String testName) {
   // throw new UnsupportedOperationException("Unimplemented method 'captureScreenshot'");
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmms s").format(new Date());
    String screenshotDir = System.getProperty("user.dir") + File.separator + "screenshots";
    String screenshotPath = screenshotDir + File.separator + testName + "_" + timeStamp + ".png";

    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File destFile = new File(screenshotPath);
    try {
      Files.copy(srcFile.toPath(), destFile.toPath()); //convert file obj to path obj
    } catch (IOException e) {
        e.printStackTrace();
    }

    return screenshotPath;
  }
  
}
