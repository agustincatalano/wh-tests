package com.williamhill.driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private WebDriver driver;

    /**
     * Sets up the driver
     */
    public DriverFactory() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * @return WebDriver driver
     */
    public WebDriver getWebDriver() {
        return driver;
    }
}
