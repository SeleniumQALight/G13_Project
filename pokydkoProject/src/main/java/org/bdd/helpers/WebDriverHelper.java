package org.bdd.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverHelper {

    private static WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public WebDriverHelper() {
        if (webDriver == null) {
            webDriver = initDriver();
        }
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public void quitDriver() {
        webDriver.quit();
        webDriver = null;
        logger.info("Browser was closed");
    }

    private WebDriver initDriver() {
        String browserFromProperty = System.getProperty("browser");

        if (browserFromProperty == null) {
            logger.info("Browser from property is null. Will be used Chrome browser by default");
            browserFromProperty = "chrome";
        }

        if (browserFromProperty.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if (browserFromProperty.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if (browserFromProperty.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }

        return webDriver;
    }
}