package com.williamhill.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestUtils {

    /**
     * Closes all Popups
     *
     * @param driver
     */
    public static void closeAllPopUps(WebDriver driver) {
        if (driver == null) throw new IllegalArgumentException("This method requires a non-null driver to work");
        List<WebElement> popups = driver.findElements(By.cssSelector("#modalPopup2:not([style*='none'])"));
        if (!popups.isEmpty()) {
            driver.findElement(By.id("yesBtn")).click();
        }
    }
}
