package org.qalight.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//div[contains(text(),'Invalid username/password.')]")
    private WebElement invalidLoginMessage;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    // Перевірки видимості кнопок через CommonActionsWithElements
    public void checkIsButtonSignOutVisible() {
        assert isElementDisplayed(buttonSignOut) : "Button SignOut is not visible";
        logger.info("Button SignOut is visible");
    }

    public void checkIsButtonSignOutNotVisible() {
        assert !isElementDisplayed(buttonSignOut) : "Button SignOut should not be visible";
        logger.info("Button SignOut is not visible as expected");
    }

    public void checkIsButtonSignInVisible() {
        assert isElementDisplayed(buttonSignIn) : "Button SignIn is not visible";
        logger.info("Button SignIn is visible");
    }

    public void checkIsButtonCreatePostVisible() {
        assert isElementDisplayed(buttonCreatePost) : "Button Create Post is not visible";
        logger.info("Button Create Post is visible");
    }

    public void checkLoginInputsAreNotVisible() {
        assert !isElementDisplayed(inputLogin) && !isElementDisplayed(inputPassword)
                : "Login inputs should not be visible after login";
        logger.info("Login inputs are not visible as expected");
    }

    public void checkInvalidLoginMessageIsVisible() {
        assert isElementDisplayed(invalidLoginMessage) : "Invalid login message is not visible";
        logger.info("Invalid login message is visible");
    }
}
