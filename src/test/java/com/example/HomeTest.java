package com.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.util.ConfigManager;

public class HomeTest extends BaseTest{

  @Test(enabled = true)
  public void ValidateBanner(){
    ConfigManager config = ConfigManager.getInstance();
    LoginPage lp = new LoginPage(driver);
    driver.get(config.getBaseUrl());
    lp.clickFormAuth();
    lp.login(config.getUsername() ,config.getPassword());
    lp.isLoginSuccessful(config.getExpectedUrl());
    // lp.login(prop.getProperty("username"), prop.getProperty("password"));
    // lp.isLoginSuccessful(prop.getProperty("expectedUrl"));
    HomePage hp = new HomePage(driver);
    Assert.assertTrue(hp.isLogOutBtnDisplayed(), "Home banner is NOT displayed!");
    }
  }
