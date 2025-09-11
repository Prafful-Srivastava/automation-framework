package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.example.util.WaitUtil;

public class HomePage {
  
  private WebDriver driver;

  public HomePage(WebDriver driver){
    this.driver = driver;
  }

  private By logOut = By.xpath("//a[@href = '/logout']");

  public boolean isLogOutBtnDisplayed() {
        try {
          WaitUtil wait = new WaitUtil(driver);
          return wait.waitForElementVisible(logOut, 10);
        } catch (Exception e) {
            return false;
        }
    }
}
