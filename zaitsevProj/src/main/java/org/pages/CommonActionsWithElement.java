package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElement {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, webDriverWait15;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElement(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані через @FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted in element " + getElementName(webElement));
        }catch (Exception e){
            printErrorAndStopTest();
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + " Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest();
        }
    }

    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest();
        }
    }

    protected void selectTextInDropDown(WebElement webElement, String text){
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info(text + "Text '" + text + "' was selected in DropDown " + getElementName(webElement));
        }catch (Exception e){
            printErrorAndStopTest();
        }
    }

    protected void selectValueInDropDown(WebElement webElement, String value){
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info("Value '" + value + "' was selected in DropDown " + getElementName(webElement));
        }catch (Exception e){
            printErrorAndStopTest();
        }
    }

    // check is element enabled
    protected void checkElementIsEnabled(WebElement webElement){
            Assert.assertTrue("Element is not enabled", webElement.isEnabled());
            logger.info("Element is enabled");
    }

    // is element enabled
    protected boolean isElementEnabled(WebElement webElement) {
        try {
            boolean state = webElement.isEnabled();
            logger.info(getElementName(webElement) + " Element enabled state is: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    protected void checkTextInElement(WebElement webElement, String expectedText){
        try {
            String actualText = webElement.getText();
            Assert.assertEquals("Text in element is not as expected", expectedText, actualText);
            logger.info("Text in element " + getElementName(webElement) + " is as expected: " + expectedText);
        }catch (Exception e){
            printErrorAndStopTest();
        }
    }

    protected void checkCheckBox(WebElement checkBox){
        if (!checkBox.isSelected()){
            clickOnElement(checkBox);
            logger.info("CheckBox was checked");
        }else {
            logger.info("CheckBox is already checked");
        }
    }

    protected void uncheckCheckBox(WebElement checkBox){
        if (checkBox.isSelected()){
            clickOnElement(checkBox);
            logger.info("CheckBox was unchecked");
        }else {
            logger.info("CheckBox is already unchecked");
        }
    }

    protected void setCheckBoxState(WebElement checkBox, String state){
        if (state.equalsIgnoreCase("check")){
            checkCheckBox(checkBox);
        }else if (state.equalsIgnoreCase("uncheck")){
            uncheckCheckBox(checkBox);
        }else {
            logger.info("Unknown state: " + state + ". Use 'check' or 'uncheck'.");
        }
    }

    private String getElementName(WebElement webElement){
        try {
            return webElement.getAccessibleName();
        }catch (Exception e){
            return "";
        }
    }


    private void printErrorAndStopTest() {
        logger.error("Error while working with element");
        Assert.fail("Error while working with element ");
    }

    protected boolean isElementDisplayed(WebElement webElement){
        try {
            boolean state = webElement.isDisplayed();
            logger.info("Element displayed state: " + state);
            return state;
        } catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

}
