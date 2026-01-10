package org.baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.qalight.pages.PageProvider;

public class BaseTest {

    protected WebDriver webDriver;
    protected PageProvider pageProvider;
    protected Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        pageProvider = new PageProvider(webDriver);
        logger.info("Browser was opened");
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
            logger.info("Browser was closed");
        }
    }
}
