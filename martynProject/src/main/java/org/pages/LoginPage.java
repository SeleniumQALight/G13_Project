package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = ".//input[@name='username' and not(@id='username-register')]")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@name='password' and not(@id='password-register')]")
    private WebElement inputPassword;

    @FindBy(xpath = ".//form[@action='/login']//button")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[contains(text(),'Invalid username/password')]")
    private WebElement invalidLoginErrorMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
    }

    public void enterTextIntoInputUsername(String username) {
        clearAndEnterTextIntoElement(inputLogin, username);
    }

    public void enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPassword, password);
    }

    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void checkIsButtonSignInVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue("Button Sign In is not visible", isElementVisible(buttonSignIn));
            logger.info("Button Sign In is visible");
        } else {
            Assert.assertFalse("Button Sign In is visible", isElementVisible(buttonSignIn));
            logger.info("Button Sign In isn't visible");
        }
    }

    public void checkIsInvalidLoginErrorMessageVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue("Invalid Login error message isn't visible"
                    , isElementVisible(invalidLoginErrorMessage));
            logger.info("Invalid Login error message is visible");
        } else {
            Assert.assertFalse("Invalid Login error message is visible"
                    , isElementVisible(invalidLoginErrorMessage));
            logger.info("Invalid Login error message isn't visible");
        }
    }

    public void checkIsInputLoginVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue("Username input isn't visible", isElementVisible(inputLogin));
            logger.info("Username input is visible");
        } else {
            Assert.assertFalse("Username input is visible", isElementVisible(inputLogin));
            logger.info("Username input isn't visible");
        }
    }

    public void checkIsInputPasswordVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue("Password input isn't visible", isElementVisible(inputPassword));
            logger.info("Password input is visible");
        } else {
            Assert.assertFalse("Password input is visible", isElementVisible(inputPassword));
            logger.info("Password input isn't visible");
        }
    }
}
