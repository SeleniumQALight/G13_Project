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
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //initialise elements described with @FindBy
    }

    protected void isRedirectedToPage(String url){
       Assert.assertTrue("Current URL does not contain: " + url +
               "\nActual URL: " + webDriver.getCurrentUrl(), webDriver.getCurrentUrl().contains(url));
    }

    protected void isNotRedirectedToPage(String url){
        Assert.assertFalse("Current URL contains: " + url +
                "\nActual URL: " + webDriver.getCurrentUrl(), webDriver.getCurrentUrl().contains(url));
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text){
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
        }catch (Exception e){
            printErrorAndStopTest();
        }
    }

    protected void clickOnElement(WebElement webElement){
        try {
            webElement.click();
            logger.info("Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest();
        }
    }

    protected void selectTextInDropDown(WebElement webElement, String value){
        try{
            Select select = new Select(webElement);
            select.selectByVisibleText(value);
            logger.info("Text '" + value + "' was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected boolean isElementVisible(WebElement webElement) {
        try {
            return webElement != null && webElement.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            logger.info("Element is not present or become stale: " + e.getMessage());
            return false;
        } catch (Exception e) {
            printErrorAndStopTest();
            return false;
        }
    }

    protected void checkIsElementEnabled(WebElement webElement){
        Assert.assertTrue("Element is not enabled", isElementEnabled(webElement));
        logger.info("Element is enabled");
    }

    protected boolean isElementEnabled(WebElement webElement) {
        try {
            boolean state = webElement.isEnabled();
            logger.info("Element enabled state is: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not enabled");
            return false;
        }
    }

    protected void checkTextInElement(WebElement webElement, String expectedText) {
        try {
            String actualText = webElement.getText();
            Assert.assertEquals("Text in element doesn't match expected text", expectedText, actualText);
            logger.info("Text in element matches expected text " + expectedText);
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    public void checkCheckbox(WebElement webElement) {
        try {
            if (!webElement.isSelected()) {
                clickOnElement(webElement);
                logger.info("Checkbox was checked");
            } else {
                logger.info("Checkbox is already checked");
            }
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    public void uncheckCheckbox(WebElement webElement) {
        try {
            if (webElement.isSelected()) {
                clickOnElement(webElement);
                logger.info("Checkbox was unchecked");
            } else {
                logger.info("Checkbox is already unchecked");
            }
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    public void setCheckboxState(WebElement webElement, String state) {
        try {
            if ("check".equalsIgnoreCase(state)) {
                checkCheckbox(webElement);
            } else if ("uncheck".equalsIgnoreCase(state)) {
                uncheckCheckbox(webElement);
            } else {
                logger.info("Unknown checkbox state: " + state);
                Assert.fail("Unknown checkbox state: " + state);
            }
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    private void printErrorAndStopTest() {
        logger.error("Error while working with element");
        Assert.fail("Error while working with element");
    }
}
