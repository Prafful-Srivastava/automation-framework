package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.util.WaitUtil;

public class LoginPage {
  private WebDriver driver;
  private WaitUtil wait;
  
  public LoginPage(WebDriver driver){
    this.driver = driver;
    this.wait = new WaitUtil(driver);
  }

  //Locators
  
  private By formAuth = By.xpath("//a[@href = '/login']");
  private By usernameField = By.id("username");
  private By passwordField = By.id("password");
  private By loginBtn = By.className("radius");

   public void clickFormAuth() {
      WebElement element = wait.waitForElementClickable(formAuth, 10);
      element.click();
      //driver.findElement(formAuth).click();
    }

  public void enterUsername(String username) {
      driver.findElement(usernameField).sendKeys(username);
    }

  public void enterPassword(String password) {
      driver.findElement(passwordField).sendKeys(password);
    }

  public void clickLoginBtn() {
      WebElement element = wait.waitForElementClickable(loginBtn, 10);
      element.click();
      //driver.findElement(loginBtn).click();
    }

  public void login(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    clickLoginBtn();
  }

  // LoginPage.java
  public boolean isLoginSuccessful(String expectedUrl) {
    //String currentUrl = driver.getCurrentUrl();
    boolean urlContains = wait.waitForUrlContains(expectedUrl, 10);
    return urlContains;
    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // wait.until(ExpectedConditions.urlContains(expectedUrl));
    
  }

}
