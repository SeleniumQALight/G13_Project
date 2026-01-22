package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }


    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String text) {
        clearAndEnterTextIntoElement(inputLogin, text);
        logger.info(text + " was entered in input UserName");
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String text) {
        clearAndEnterTextIntoElement(inputPassword, text);
        logger.info("Password was entered");
        return this;
    }

//    public HomePage clickOnButtonSignIn() {
//        clickOnElement(buttonSignIn);
//        return new HomePage(webDriver);
//    }

    public HomePage clickOnButtonSignIn() {
        try {
            logger.info("Clicking Sign In button");
            clickOnElement(buttonSignIn);
            logger.info("Sign In button was clicked");
            return new HomePage(webDriver);
        } catch (Exception e) {
            logger.error("Cannot click Sign In button", e);
            throw new RuntimeException("Cannot click Sign In button", e);
        }
    } //Checked this method for exception handling Accidently deleted the commented method above

    public HomePage openLoginPageAndFillLoginFormWithVailidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.ValidLogin);
        enterTextIntoInputPassword(TestData.ValidPassword);
        return clickOnButtonSignIn();
    }
}

