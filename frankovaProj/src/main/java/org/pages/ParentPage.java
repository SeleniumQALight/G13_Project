package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.utils.ConfigProvider;

abstract public class ParentPage extends CommonActionsWithElements {
    String environment = System.getProperty("env", "aqa");

//protected String baseUrl = "https://"+environment+"-complexapp.onrender.com";

    protected String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    private Logger logger = Logger.getLogger(getClass());

    abstract protected String getRelativeURL();//метод який буде повертати відносну частину URL для кожної сторінки, наприклад для HomePage це буде "/home"

    protected void checkUrl() {
        Assert.assertEquals("URL is not expected"
                , baseUrl + getRelativeURL() //expected url
                , webDriver.getCurrentUrl());//current url
    }


    // метод по перевірці чи відкрита потрібна сторінка по патерну
    // https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    protected void checkUrlWithPattern() {
        Assert.assertTrue("URL is not expected\n" +
                        "Expected url: " + baseUrl + getRelativeURL() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeURL())); //matches вміє працювати з regex
    }


    //open new tab in browser using javascript
    public void openNewTabInBrowser() {
        try {
            ((org.openqa.selenium.JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab in browser was opened");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    //switch to new tab
    public void switchToTabInBrowser(int tabNumber) {
        try {
            java.util.ArrayList<String> tabs = new java.util.ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(tabNumber));
            logger.info("Switched to tab number " + tabNumber);
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    //close tab in browser
    public void closeTabInBrowser(int tabNumber) {
        try {
            java.util.ArrayList<String> tabs = new java.util.ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(tabNumber));
            webDriver.close();
            logger.info("Closed tab number " + tabNumber);
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    //refresh page in browser
    public void refreshPageInBrowser() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page in browser was refreshed");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    //press tab key on keyboard Actions n-times
    public void pressTabKeyOnKeyboard() {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(org.openqa.selenium.Keys.TAB).build().perform();
            logger.info("Tab key on keyboard was pressed");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    //press enter key with actions
    public void pressEnterKeyOnKeyboard() {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(org.openqa.selenium.Keys.ENTER).build().perform();
            logger.info("Enter key on keyboard was pressed");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    //enter text in input with Actions without element
    public void enterTextInInputWithActions(String text) {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(text).build().perform();
            logger.info("Text '" + text + "' was entered in input with Actions");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }
}
