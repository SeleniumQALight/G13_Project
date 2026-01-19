package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;
import org.testdata.TestData;

public class HomePage extends ParentPage{

    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createNewPostButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public HomePage checkIsButtonSignOutVisible(){
        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        logger.info("Button Sign Out is visible");
        return this;
    }

    public boolean isButtonSignOutVisible() {
        try{
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element state: " + state);
            return state;
        } catch (Exception e){
            logger.info("Element is not found");
            return false;
        }
    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO Check URL
        //TODO check some unique element on HomePage
        return this;
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(createNewPostButton);
        return new CreatePostPage(webDriver);
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
                    .enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI)
                    .clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }


    public HomePage checkIsButtonCreatePostVisible() {
        Assert.assertTrue("Button Create Post is not visible", isButtonCreatePostVisible());
        logger.info("Button Create Post is visible");
        return this;
    }

    private boolean isButtonCreatePostVisible() {
        try{
            boolean state = createNewPostButton.isDisplayed();
            logger.info("Element state: " + state);
            return state;
        } catch (Exception e){
            logger.info("Element is not found");
            return false;
        }
    }


}
