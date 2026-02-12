package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utils.ConfigProvider;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;

    protected WebDriverWait webDriverWait10, webDriverWait15;

    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані через findby
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider
                .configProperties.TIME_FOR_EXPLICIT_WAIT_LOW(

                )));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider
                .configProperties.TIME_FOR_DEFAULT_WAIT()));
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
     try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info( elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info( elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }


    protected void selectTextInDropDown(WebElement webElement, String text) {

        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text '" + text + "' was selected in DropDown " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

     //analog for selectTextInDropDown
  /*  protected void selectValueInDropDown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info("Value '" + value + "' was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }*/


    protected void checkTextInElement(WebElement webElement, String expectedText) {
        try {
            String actualText = webElement.getText();
            Assert.assertEquals(getElementName(webElement) + "Text in element is not as expected", expectedText, actualText);
            logger.info("Text in element is as expected: " + expectedText);
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }




    protected void checkCheckbox(WebElement webElement) {
        try {
            if (!webElement.isSelected()) {
                clickOnElement(webElement);
                logger.info(getElementName(webElement) + " Checkbox was checked");
            } else {
                logger.info(getElementName(webElement)  + " Checkbox is already checked");
            }
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }


    protected void enterStateForCheckbox(String state, WebElement webElement) {
        if (state == null || state.trim().isEmpty()) {
            logger.info("State for checkbox is null or empty");
            throw new AssertionError("State for checkbox is null or empty");
        }

        String s = state.trim().toLowerCase();
        switch (s) {
            case "check":
                checkCheckbox(webElement);
                break;
            case "uncheck":
                uncheckCheckbox(webElement);
                break;
            default:
                logger.info("State for checkbox is incorrect: " + state);
                throw new AssertionError("Incorrect checkbox state: " + state);
        }
    }


    protected void uncheckCheckbox(WebElement webElement) {
        try {
            if (webElement.isSelected()) {
                clickOnElement(webElement);
                logger.info(getElementName(webElement) + " Checkbox was unchecked");
            } else {
                logger.info(getElementName(webElement)  + " Checkbox is already unchecked");
            }
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }


    //аналог до checkIsElementEnabled
    /*protected void checkIsElementVisible(WebElement webElement){
        Assert.assertTrue(webElement + " Element is not visible", isElementVisible(webElement));
        logger.info(webElement + " Element is visible");
    }*/

    protected void checkIsNotElementVisible(WebElement webElement) {
        Assert.assertFalse(getElementName(webElement) + " Element is visible", isElementVisible(webElement));
        logger.info(webElement + " Element is NOT visible");
    }

    protected boolean isElementVisible (WebElement webElement){
        try {
            boolean state = webElement.isDisplayed();
            logger.info(getElementName(webElement) + " Element visible state: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }

    }

    //check is element enabled
    protected void checkIsElementEnabled(WebElement webElement) {
        Assert.assertTrue("Element is not enabled", isElementEnabled(webElement));
        logger.info(getElementName(webElement) + " Element is enabled");
    }

    //is element enabled
    protected boolean isElementEnabled(WebElement webElement) {
        try {
            boolean state = webElement.isEnabled();
            logger.info(getElementName(webElement)  + " Element enabled state: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

     protected void printErrorAndStopTest () {
         logger.error("Error while working with element");
         Assert.fail("Error while working with element");
     }


}
