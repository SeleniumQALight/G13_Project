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

    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputUsername(String username) {
        clearAndEnterTextIntoElement(inputLogin, username);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String password) {
        clearAndEnterTextIntoElement(inputPassword, password);
        return this;
    }

    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public LoginPage checkIsButtonSignInVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue("Button Sign In is not visible", isElementVisible(buttonSignIn));
            logger.info("Button Sign In is visible");
        } else {
            Assert.assertFalse("Button Sign In is visible", isElementVisible(buttonSignIn));
            logger.info("Button Sign In isn't visible");
        }
        return this;
    }

    public LoginPage checkIsInvalidLoginErrorMessageVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue("Invalid Login error message isn't visible"
                    , isElementVisible(invalidLoginErrorMessage));
            logger.info("Invalid Login error message is visible");
        } else {
            Assert.assertFalse("Invalid Login error message is visible"
                    , isElementVisible(invalidLoginErrorMessage));
            logger.info("Invalid Login error message isn't visible");
        }
        return this;
    }

    public LoginPage checkIsInputLoginVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue("Username input isn't visible", isElementVisible(inputLogin));
            logger.info("Username input is visible");
        } else {
            Assert.assertFalse("Username input is visible", isElementVisible(inputLogin));
            logger.info("Username input isn't visible");
        }
        return this;
    }

    public LoginPage checkIsInputPasswordVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue("Password input isn't visible", isElementVisible(inputPassword));
            logger.info("Password input is visible");
        } else {
            Assert.assertFalse("Password input is visible", isElementVisible(inputPassword));
            logger.info("Password input isn't visible");
        }
        return this;
    }

    public HomePage getLoginPageAndFillLoginFormWithValidCreds(String username, String password) {
        openLoginPage();
        enterTextIntoInputUsername(username);
        enterTextIntoInputPassword(password);
        clickButtonSignIn();
        logger.info("Login form was filled with valid credentials");
        return new HomePage(webDriver);
    }
}
