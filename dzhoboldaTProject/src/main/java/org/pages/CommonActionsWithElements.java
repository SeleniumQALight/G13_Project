package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utils.ConfigProvider;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, webDriverWait15;
    private final Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element" + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(getElementName(webElement) + "Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void selectTextInDropDown(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text" + text + " was selected in DropDown" + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void selectValueInDropDown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info("Value" + value + " was selected in DropDown" + getElementName(webElement));
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
            logger.info(getElementName(webElement) + "Element is enabled - " + state);
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
            logger.info("Text in element " + getElementName(webElement) +"is as expected: " + text);
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

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "Unknown element";
        }
    }

    protected void checkIsElementDisplayed(WebElement webElement) {
        Assert.assertTrue(
                "Element is not displayed",
                isElementDisplayed(webElement)
        );
        logger.info("Element is displayed");
    }

    protected void checkIsElementNotDisplayed(WebElement webElement) {
        Assert.assertFalse(
                "Element is displayed but should not be",
                isElementDisplayed(webElement)
        );
        logger.info("Element is not displayed");
    }


    private void printErrorAndStopTest() {
        logger.error("Error while working with element ");
        Assert.fail("Error while working with element " );
    }
}










