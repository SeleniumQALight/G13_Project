package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
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
    private static Logger logger = Logger.getLogger(CommonActionsWithElements.class);

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // init elements declare by "@FindBy"
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(
                ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(
                ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_HIGH()));
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputed into element " + getElementName(webElement));
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
            logger.info(elementName + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    public void pressEnterKey(WebElement webElement) {
        Actions actions = new Actions(webDriver);
        actions
                .moveToElement(webElement)
                .click()
                .sendKeys("\n")
                .perform();
        logger.info("Enter key was pressed using Actions");
    }

    public void pressEnterKey() {
        Actions actions = new Actions(webDriver);
        actions
                .sendKeys("\n")
                .perform();
        logger.info("Enter key was pressed using Actions");
    }

    // check is element enabled
    protected void checkElementIsEnabled(WebElement webElement) {
        Assert.assertTrue("Element is not enabled", isElementEnabled(webElement));
        logger.info(getElementName(webElement) + "Element is enabled");
    }

    // is element enabled
    protected boolean isElementEnabled(WebElement webElement) {
        try {
            boolean state = webElement.isEnabled();
            logger.info(getElementName(webElement) + " element enabled status: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not enabled");
            return false;
        }
    }

    protected void checkTextInElement(WebElement webElement, String expectedText) {
        try {
            String actualText = webElement.getText();
//            Assert.assertEquals("Text in element is not as expected", expectedText, actualText);
            Assert.assertTrue(
                    "Text in element is not as expected. " +
                            "Expected (equals or contains): " + expectedText +
                            ", Actual: " + actualText,
                    actualText.equals(expectedText) || actualText.contains(expectedText)
            );

            logger.info("Text in element matches expected text: " + expectedText);
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void selectCheckbox(WebElement webElement) {
        try {
            if (!webElement.isSelected()) {
                clickOnElement(webElement);
            }
            Assert.assertTrue("Checkbox should be selected", webElement.isSelected());
            logger.info("Checkbox is selected");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void unselectCheckbox(WebElement webElement) {
        try {
            if (webElement.isSelected()) {
                clickOnElement(webElement);
            }
            Assert.assertFalse("Checkbox should be unselected", webElement.isSelected());
            logger.info("Checkbox is unselected");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void setCheckboxState(WebElement webElement, String state) {
        try {
            if ("check".equalsIgnoreCase(state)) {
                selectCheckbox(webElement);
            } else if ("uncheck".equalsIgnoreCase(state)) {
                unselectCheckbox(webElement);
            } else {
                Assert.fail("Incorrect checkbox state: " + state +
                        ". Expected: 'check' or 'uncheck'");
            }
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    protected static void printErrorAndStopTest() {
        logger.error("Error while working with element");
        Assert.fail("Error while working with element"); // wrote info into report
    }

}