package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import org.utils.ConfigProvider;

abstract public class ParentPage extends CommonActionsWithElements {
    Logger logger = Logger.getLogger(getClass());


    String environment = System.getProperty("env", "aqa");
//    protected String baseUrl = "https://" + environment + "-complexapp.onrender.com";

    protected String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("URL is not expexted"
                , baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
    }

    // метод по перевірці чи відкрита потрібна сторінка по патерну
    // https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseUrl + getRelativeUrl() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }

    protected void focusOnElementViaTab() {
        try {
            Actions actions = new Actions(webDriver);
            actions
                    .sendKeys(Keys.TAB)
                    .perform();
            logger.info("Focus moved via TAB");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page was refreshed");
    }

    public void openNewTab() {
        try {
            String currentUrl = webDriver.getCurrentUrl();
            ((JavascriptExecutor) webDriver)
                    .executeScript("window.open(arguments[0]);", currentUrl);
        } catch (Exception e) {
            printErrorAndStopTest();
        }

    }

    public void switchToTab(int tabIndex) {
        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabIndex));
        logger.info("Switched to tab with index " + tabIndex);
    }
    public void closeTab(int tabIndex) {
        try {
            List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());

            if (tabIndex < 0 || tabIndex >= tabs.size()) {
                throw new IllegalArgumentException("Invalid tab index: " + tabIndex);
            }

            webDriver.switchTo().window(tabs.get(tabIndex));
            webDriver.close();

            logger.info("Closed tab with index " + tabIndex);
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

}