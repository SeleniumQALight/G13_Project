package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createNewPostButton;
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public boolean isButtonSignOutVisible(){
        return isElementDisplayed(buttonSignOut);
    }

    public void checkIsButtonSignOutVisible(){
        Assert.assertTrue("Button SignOut is not visible", isButtonSignOutVisible());
        logger.info("Button SignOut is visible");

    }
    public void checkIsButtonCreatePostVisible(){
        Assert.assertTrue("Button Create Post is not visible", isElementDisplayed(buttonCreatePost));
    }

    public HomePage checkIsRedirectToHomePage() {
        // TODO check URL
        // TODO check some unique element on HomePage
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
            return this;
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
                    .enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI)
                    .clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");

        }
        return this;
    }


}
