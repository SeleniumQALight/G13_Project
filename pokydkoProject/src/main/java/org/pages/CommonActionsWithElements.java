package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані через @FindBy
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + "was inputted into element");
        } catch (Exception e) {
          printErrorAndStopTest();
        }
    }

    protected void clickOnElement(WebElement webElement){
        try{
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e){
            printErrorAndStopTest();
        }
    }

    protected void selectTextInDropDown(WebElement webElement, String text){
        try{
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text '" + text + "' was selected in DropDown");
        } catch (Exception e){
            printErrorAndStopTest();
        }
    }

    protected void selectValueInDropDown(WebElement webElement, String value){
        try{
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info("Value '" + value + "' was selected in DropDown");
        } catch (Exception e){
            printErrorAndStopTest();
        }
    }

    //check is element enabled
    protected void checkIsElementEnabled(WebElement webElement){
        Assert.assertTrue("Element is not enabled", isElementEnabled(webElement));
        logger.info("Element is enabled");
    }
    //is element enabled
    protected boolean isElementEnabled(WebElement webElement){
        try{
            boolean state = webElement.isEnabled();
            logger.info("Element is enabled" + state);
            return state;
        } catch (Exception e){
            logger.error("Element is not found");
            return false;
        }
    }

    protected void checkTextInElement(WebElement webElement, String expectedText){
        try{
            String actualText = webElement.getText();
            Assert.assertEquals("Text in element does not match expected text", expectedText, actualText);
            logger.info("Text in element matches expected text: " + expectedText);
        } catch (Exception e){
            printErrorAndStopTest();
        }
    }


    private void printErrorAndStopTest() {
    logger.error("Error while working with element");
    Assert.fail("Error while working with element");
    }

}
