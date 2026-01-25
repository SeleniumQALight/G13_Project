package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані через @FindBy
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

    private void printErrorAndStopTest() {
        logger.error("Error while working with element");
        Assert.fail("Error while working with element");
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
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
            logger.info("Text '" + text + "' was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    protected void selectValueInDropDown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(value);
            logger.info("Value '" + value + "' was selected in DropDown");
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
            logger.info("Element enabled - " + state);
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
            logger.info("Text in element matches expected text: " + expectedText);
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

    public HomePage openNewTab() {
        ((JavascriptExecutor) webDriver).executeScript("window.open();");
        logger.info("New tab was opened");
        return new HomePage(webDriver); // Повертаємо об'єкт сторінки
    }

    public HomePage switchToTab(int tabIndex) {
        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabIndex));
        logger.info("Switched to tab with index " + tabIndex);
        return new HomePage(webDriver); // Тепер ланцюжок знає, що далі йде HomePage
    }

    public HomePage closeCurrentTabAndSwitchToMain(int mainTabIndex) {
        webDriver.close();
        switchToTab(mainTabIndex);
        logger.info("Current tab was closed and switched to main tab");
        return new HomePage(webDriver);
    }

    public LoginPage refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page was refreshed");
        return new LoginPage(webDriver);
    }










    //ці 2 методи були додані в hw5, тому потрібно видалити їх з hw6 після мержу hw5 в main:
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
