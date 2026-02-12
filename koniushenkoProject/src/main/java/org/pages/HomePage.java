package org.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HederForLoggedUserElement;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createNewPostButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }


    public HederForLoggedUserElement getHederForLoggedUserElement() {
        return  new HederForLoggedUserElement(webDriver);
    }
    @Step
    public void checkIsButtonSignOutVisible(){
        Assert.assertTrue("Button Sign Out is NOT visible",isButtonSingOutVisible());
        logger.info("Button Sign Out is visible");
    }

    @Step
    public void checkIsButtonSignOutNotVisible(){
        Assert.assertFalse("Button Sign Out is visible",isButtonSingOutVisible());
        logger.info("Button Sign Out is not visible");
    }
    @Step
    public boolean isButtonSingOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(
                    "//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element state:" + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }
    @Step
    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        return this;
    }
    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(createNewPostButton);
        return new CreatePostPage(webDriver);
    }
    @Step
    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (isButtonSingOutVisible()) {
            logger.info("User is already logged in");
        } else {
           loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
                     .enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI)
                     .clickOnButtonSignIn();
           checkIsRedirectToHomePage();
           logger.info("User was logged in");
        }
        return  this;
    }

    @Step
    public void checkIsButtonCreatePostVisible(){
        Assert.assertTrue("Button CreatePost is NOT visible",isButtonCreatePostVisible());
        logger.info("Button CreatePost is visible");
    }
    @Step
    public boolean isButtonCreatePostVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(
                    "//a[text()='Create Post']")).isDisplayed();
            logger.info("Element state:" + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }
}
