package org.qalight.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[contains(text(),'Invalid username/password.')]")
    private WebElement invalidLoginMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page opened");
    }

    public void enterLogin(String login) {
        clearAndEnterTextIntoElement(inputLogin, login);
    }

    public void enterPassword(String password) {
        clearAndEnterTextIntoElement(inputPassword, password);
    }

    public void clickOnSignInButton() {
        clickOnElement(buttonSignIn);
    }

    public void checkIsSignInButtonVisible() {
        Assert.assertTrue("Sign In button is not visible",
                isElementDisplayed(buttonSignIn));
    }

    public void checkInvalidLoginMessageIsVisible() {
        Assert.assertTrue("Invalid login message is not visible",
                isElementDisplayed(invalidLoginMessage));
    }

    public void checkLoginInputsAreNotVisible() {
        Assert.assertFalse("Login input should not be visible",
                isElementDisplayed(inputLogin));
        Assert.assertFalse("Password input should not be visible",
                isElementDisplayed(inputPassword));
    }
}
