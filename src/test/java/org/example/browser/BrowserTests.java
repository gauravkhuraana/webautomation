package org.example.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.example.base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserTests extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BrowserTests.class);
    private static final String TARGET_URL = "https://udzial.com";

    @Test(groups = "TESTCASE_22")
    public void openInChrome() {
        try {
            logger.info("Initializing Chrome browser");
            initializeDriver(new ChromeDriver());
            driver.get(TARGET_URL);
            logger.info("Successfully opened {} in Chrome", TARGET_URL);
        } catch (Exception e) {
            logger.error("Failed to execute Chrome test", e);
            throw e;
        } finally {
            tearDown();
            logger.info("Chrome browser session ended");
        }
    }

    @Test(groups = "TESTCASE_26")
    public void openInEdge() {
        try {
            logger.info("Initializing Edge browser");
            WebDriver driver = new EdgeDriver();
            driver.get(TARGET_URL);
            logger.info("Successfully opened {} in Edge", TARGET_URL);
        } finally {
            driver.quit();
            logger.info("Edge browser session ended");
        }
    }

    @Test(groups = "TESTCASE_27")
    public void openInFirefox() {
        try {
            logger.info("Initializing Firefox browser");
            WebDriver driver = new FirefoxDriver();
            driver.get(TARGET_URL);
            logger.info("Successfully opened {} in Firefox", TARGET_URL);
        } finally {
            driver.quit();
            logger.info("Firefox browser session ended");
        }
    }
}