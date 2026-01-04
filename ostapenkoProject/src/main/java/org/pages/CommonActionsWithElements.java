package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // init elements declare by "@FindBy"
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputed into element");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void checksElementVisible(WebElement webElement, String name) {
        try {
            Assert.assertTrue(name + " is not visible", webElement.isDisplayed());
        } catch (Exception e) {
            Assert.fail(name + " is not visible");
        }
    }

    protected void checksElementNotVisible(WebElement webElement, String name) {
        try {
            if (webElement.isDisplayed()) {
                Assert.fail(name + " is visible, but should NOT be");
            } else {
                logger.info(name + " is NOT visible");
            }
        } catch (NoSuchElementException e) {
            logger.info(name + " is NOT present in DOM, considered NOT visible");
        } catch (StaleElementReferenceException e) {
            logger.info(name + " is stale/removed, considered NOT visible");
        }
    }


    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    private void printErrorAndStopTest() {
        logger.error("Error while working with element");
        Assert.fail("Error while working with element"); // wrote info into report
    }
}