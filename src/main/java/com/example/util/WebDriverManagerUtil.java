package com.example.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverManagerUtil {
  
  private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

  public static void createDriver() {
    WebDriver webDriver = null;
    ConfigManager config = ConfigManager.getInstance();
    //String browser = config.getBrowser(); // ideal only when running in local
    String browser = System.getProperty("browser", config.getBrowser());
    boolean remote = Boolean.parseBoolean(System.getProperty("remote", String.valueOf(config.isRemote())));
    String remoteUrl = System.getProperty("remote.url", config.getRemoteUrl());
    boolean headless = Boolean.parseBoolean(System.getProperty("headless", String.valueOf(config.isHeadless())));

    // Fallback to default if null or empty
    if (browser == null || browser.isBlank()) {
        browser = "chrome";
    }

    // if(browser.equalsIgnoreCase("chrome")){
    //     webDriver = new ChromeDriver();
    // } else if(browser.equalsIgnoreCase("firefox")) {
    //     webDriver = new FirefoxDriver();
    // } else if(browser.equalsIgnoreCase("edge")) {
    //     webDriver = new EdgeDriver();
    // }

    if ("chrome".equalsIgnoreCase(browser)) {
          ChromeOptions options = new ChromeOptions();
          if (headless) options.addArguments("--headless=new", "--window-size=1920,1080");
          webDriver = createDriverInstance(options, remote, remoteUrl);
      } else if ("firefox".equalsIgnoreCase(browser)) {
          FirefoxOptions options = new FirefoxOptions();
          if (headless) options.addArguments("--headless");
          webDriver = createDriverInstance(options, remote, remoteUrl);
      } else if ("edge".equalsIgnoreCase(browser)) {
          EdgeOptions options = new EdgeOptions();
          if (headless) options.addArguments("headless");
          webDriver = createDriverInstance(options, remote, remoteUrl);
      } else {
          throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

    setDriver(webDriver);
    }

  private static WebDriver createDriverInstance(Object options, boolean remote, String remoteUrl) {
        try {
            if (remote) {
                return new RemoteWebDriver(new URL(remoteUrl), (org.openqa.selenium.Capabilities) options);
            } else {
                if (options instanceof ChromeOptions) {
                    return new ChromeDriver((ChromeOptions) options);
                } else if (options instanceof FirefoxOptions) {
                    return new FirefoxDriver((FirefoxOptions) options);
                } else if (options instanceof EdgeOptions) {
                    return new EdgeDriver((EdgeOptions) options);
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL: " + remoteUrl, e);
        }
        throw new IllegalArgumentException("Unsupported options: " + options);
    }
  
    public static WebDriver getDriver(){
    return driver.get();
  }

  // Setter for driver (will be used internally)
  public static void setDriver(WebDriver webDriver) {
    driver.set(webDriver);
  }

  public static void quitDriver(){
    if (driver.get() != null) {
        driver.get().quit();
        driver.remove();
    }
  }
}
