package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані через findby
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void clickOnElement (WebElement webElement){
        try{
            webElement.click();
            logger.info(webElement+ " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }
    protected void checkIsElementVisible(WebElement webElement){
        Assert.assertTrue(webElement + " Element is not visible", isElementVisible(webElement));
        logger.info(webElement + " Element is visible");
    }

    protected void checkIsNotElementVisible(WebElement webElement){
        Assert.assertFalse(webElement + " Element is visible", isElementVisible(webElement));
        logger.info(webElement + " Element is NOT visible");
    }

    protected boolean isElementVisible(WebElement webElement){
        try {
            boolean state = webElement.isDisplayed();
            logger.info(webElement + " Element state: " + state);
            return state;
        } catch (Exception e) {
            logger.info(webElement + " Element is not found");
            return false;
        }
    }

    private void printErrorAndStopTest() {
        logger.error("Error while working with element");
        Assert.fail("Error while working with element");
    }


}
