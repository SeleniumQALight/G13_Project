package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private final Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізує елементи описані через @FindBy
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
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

    protected void selectTextInDropDown(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text" + text + " was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void selectValueInDropDown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info("Value" + value + " was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    //check is element enabled
    protected void checkIsElementEnabled(WebElement webElement) {
        Assert.assertTrue("Element is not enabled", webElement.isEnabled());
        logger.info("Element is enabled");
    }

    protected boolean isElementEnabled(WebElement webElement) {
        try {
            boolean state = webElement.isEnabled();
            logger.info("Element is enabled - " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean isDisplayed = webElement.isDisplayed();
            logger.info("Element is displayed - " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }
    protected void checkTextInElement(WebElement webElement, String text) {
        try {
            String actualText = webElement.getText();
            Assert.assertEquals("Text in element is not as expected", text, webElement.getText());
            logger.info("Text in element is as expected: " + text);
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    // A method to set a checkbox to selected
    protected void setCheckboxSelected(WebElement webElement) {
        try {
            if (!webElement.isSelected()) {
                clickOnElement(webElement);
                logger.info("Checkbox was selected");
            } else {
                logger.info("Checkbox is already selected");
            }
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    // Method for deselecting a checkbox
    protected void setCheckboxUnselected(WebElement webElement) {
        try {
            if (webElement.isSelected()) {
                clickOnElement(webElement);
                logger.info("Checkbox was unselected");
            } else {
                logger.info("Checkbox is already unselected");
            }
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    // Universal method of setting the state
    protected void setStatusToCheckbox(WebElement webElement, String state) {
        if (state.equalsIgnoreCase("check")) {
            setCheckboxSelected(webElement);
        } else if (state.equalsIgnoreCase("uncheck")) {
            setCheckboxUnselected(webElement);
        } else {
            logger.error("State should be 'check' or 'uncheck'");
            Assert.fail("State should be 'check' or 'uncheck'");
        }
    }

    private void printErrorAndStopTest() {
        logger.error("Error while working with element ");
        Assert.fail("Error while working with element " );
    }
}










