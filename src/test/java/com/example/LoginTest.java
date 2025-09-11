package com.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.util.ConfigManager;

//@Listeners(com.example.util.TestListener.class)
public class LoginTest extends BaseTest{

//   @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
//   public void testLogin(String username, String password) {
//     // use username and password
// }

  @Test(enabled = true)
  public void login(){
    ConfigManager config = ConfigManager.getInstance();
    LoginPage lp = new LoginPage(driver);
    driver.get(config.getBaseUrl());
    Assert.assertEquals(driver.getTitle(), "The Internet");
    lp.clickFormAuth();
    
    lp.login(config.getUsername() , config.getPassword());
    //lp.isLoginSuccessful(prop.getProperty("expectedUrl"));
    lp.isLoginSuccessful(config.getExpectedUrl());

  }
}
