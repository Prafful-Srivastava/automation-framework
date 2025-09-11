package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager instance;  // Singleton instance
    private Properties prop;
    private String baseUrl;
    private String browser;
    private String username;
    private String password;
    private String expectedUrl;

    // Private constructor ensures no external instantiation
    private ConfigManager() {
        loadProperties();
    }

    // Public method to get the singleton instance
    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    // Load properties from config.properties
    private void loadProperties() {
        prop = new Properties();
        try (InputStream input = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in resources folder");
            }
            prop.load(input);
            baseUrl = prop.getProperty("baseUrl");
            browser = prop.getProperty("browser");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            expectedUrl = prop.getProperty("expectedUrl");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    // Getter methods
    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBrowser() {
        return browser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedUrl() {
        return expectedUrl;
    }

    public String getRemoteUrl() {
        return prop.getProperty("remote.url", "http://localhost:4444/wd/hub");
    }

    public boolean isRemote() {
        return Boolean.parseBoolean(prop.getProperty("remote", "false"));
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(prop.getProperty("headless", "false"));
    }


    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}
