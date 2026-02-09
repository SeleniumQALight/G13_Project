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

     private void printErrorAndStopTest () {
         logger.error("Error while working with element");
         Assert.fail("Error while working with element");
     }


}
