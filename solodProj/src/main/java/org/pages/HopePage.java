package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class HopePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createNewPostButton;

    public HopePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSingOutVisible() {
        Assert.assertTrue("Button SingOut is not visible", isButtonSingOutVisible());
        logger.info("Button SingOut is visible");
    }

    public boolean isButtonSingOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element state" + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;

        }
    }

    public HopePage checkIsRedirectToHomePage() {
        //TODO check URL
        //TODO check some unique element on HomePage
        return this;
    }

    public CreatePostPage clickOnButtonCreateNewPost() {
        clickOnElement(createNewPostButton);
        return new CreatePostPage(webDriver);
    }


    public HopePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (isButtonSingOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextInputLogin(TestData.VALID_LOGIN_UI)
                    .enterTextInputPassword(TestData.VALID_PASSWORD_UI)
                    .clickOnButtonSingIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }
}
