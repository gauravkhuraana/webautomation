package org.example;

import org.openqa.selenium.WebDriver;

public class TestBase {
    protected WebDriver driver;

    protected void initializeDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    protected void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}