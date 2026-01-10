package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
                Assert.assertTrue(name + " is NOT visible", true);
                logger.info(name + " is NOT visible");
            }
        } catch (NoSuchElementException e) {
            Assert.assertTrue(name + " is NOT present in DOM, considered NOT visible", true);
            logger.info(name + " is NOT present in DOM, considered NOT visible");
        } catch (StaleElementReferenceException e) {
            Assert.assertTrue(name + " is stale/removed, considered NOT visible", true);
            logger.info(name + " is stale/removed, considered NOT visible");
        }
    }


    protected void selectTextInDropDown(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text '" + text + "' was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void selectValueInDropDown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info("Value '" + value + "' was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest();
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

    // check is element enabled
    protected void checkElementIsEnabled(WebElement webElement) {
        Assert.assertTrue("Element is not enabled", isElementEnabled(webElement));
        logger.info("Element is enabled");
    }

    // is element enabled
    protected boolean isElementEnabled(WebElement webElement) {
        try {
            boolean state = webElement.isEnabled();
            logger.info("Element enabled status: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not enabled");
            return false;
        }
    }

    protected void checkTextInElement(WebElement webElement, String expectedText) {
        try {
            String actualText = webElement.getText();
            Assert.assertEquals("Text in element is not as expected", expectedText, actualText);
            logger.info("Text in element is as expected: " + expectedText);
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    private void printErrorAndStopTest() {
        logger.error("Error while working with element");
        Assert.fail("Error while working with element"); // wrote info into report
    }
}