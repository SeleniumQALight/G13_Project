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
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані через @FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
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

    private void printErrorAndStopTest() {
        logger.error("Error while working with element");
        Assert.fail("Error while working with element");
    }

    protected void clickOnElement(WebElement webElement, String elementName){
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

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found or not displayed");
            return false;
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

    protected void selectValueInDropDown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(value);
            logger.info("Value '" + value + "' was selected in DropDown " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    //check is element enabled
    protected void checkIsElementEnabled(WebElement webElement) {
        Assert.assertTrue("Element is not enabled", isElementEnabled(webElement));
        logger.info("Element is enabled");
    }

    //is element enabled
    protected boolean isElementEnabled(WebElement webElement) {
        try {
            boolean state = webElement.isEnabled();
            logger.info(getElementName(webElement) + " Element enabled - " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    protected void checkTextInElement(WebElement webElement, String expectedText) {
        try {
            String actualText = webElement.getText();
            Assert.assertEquals("Text in element does not match expected text", expectedText, actualText);
            logger.info("Text in element " + getElementName(webElement) + " matches expected text: " + expectedText);
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    //set checkbox selected
    protected void setCheckBoxSelected(WebElement webElement) {
        try {
            if (!webElement.isSelected()) {
                webElement.click();
                logger.info("Checkbox was selected");
            } else {
                logger.info("Checkbox is already selected");
            }
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    //set checkbox unselected
    protected void setCheckBoxUnselected(WebElement webElement) {
        try {
            if (webElement.isSelected()) {
                webElement.click();
                logger.info("Checkbox was unselected");
            } else {
                logger.info("Checkbox is already unselected");
            }
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    //set status to checkbox
    public void setStatusToCheckbox(WebElement webElement, String status) {
        if (status.equalsIgnoreCase("check")) {
            setCheckBoxSelected(webElement);
        } else if (status.equalsIgnoreCase("uncheck")) {
            setCheckBoxUnselected(webElement);
        } else {
            logger.error("Invalid status for checkbox: " + status);
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

    public void checksElementVisible(WebElement element, String name) {
        try {
            Assert.assertTrue("Element " + name + " is not visible", isElementDisplayed(element));
        } catch (Exception e) {
            logger.error("Element " + name + " is not visible");
            Assert.fail("Element " + name + " is not visible"); // Тепер тест впаде по-справжньому
        }
    }

    public void checksElementNotVisible(WebElement element, String name) {
        try {
            Assert.assertFalse("Element " + name + " is visible", isElementDisplayed(element));
        } catch (Exception e) {
        }
    }
}
