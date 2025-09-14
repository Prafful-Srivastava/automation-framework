package com.example;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.example.util.ConfigManager;
import com.example.util.ExtentReportManager;
import com.example.util.WebDriverManagerUtil;

public class BaseTest {
  protected WebDriver driver;
  // protected Properties prop;
  protected String baseUrl;
  //protected String browser;

  // @BeforeSuite
  // public void loadConfigOnce() {
  //     loadProperties();
  // }

  @BeforeSuite
  public void loadConfigOnce() {
    ConfigManager config = ConfigManager.getInstance();
    baseUrl = config.getBaseUrl();
    //browser = config.getBrowser();
    String reportPath = System.getProperty("user.dir") + "/target/extent-report/index.html";
    ExtentReportManager.createInstance(reportPath);
  }

  @BeforeMethod
  public void setUp(){
    WebDriverManagerUtil.createDriver();
    driver = WebDriverManagerUtil.getDriver();
    driver.manage().window().maximize();

    // if (prop == null) { // in case BeforeSuite did not run
    //     loadProperties();
    // }
    // if (browser == null || browser.trim().isEmpty()) {
    //     ConfigManager config = ConfigManager.getInstance();
    //     browser = config.getBrowser();
    //     baseUrl = config.getBaseUrl();
    // }
  }

  // public void loadProperties(){
  //   try(InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")){
  //     //input = new FileInputStream("src/test/resources/config.properties");
  //     prop = new Properties();
  //     prop.load(input);
  //     baseUrl = prop.getProperty("baseUrl");
  //     browser = prop.getProperty("browser");

  //   } catch (IOException e) {
  //       e.printStackTrace();
  //   }
  // }


  @AfterMethod
  public void tearDown(){
      //driver.quit();
      WebDriverManagerUtil.quitDriver(); //handles null check internally
 }

 @AfterSuite
  public void generateReport() {
    ExtentReportManager.getExtent().flush();
  }
}
