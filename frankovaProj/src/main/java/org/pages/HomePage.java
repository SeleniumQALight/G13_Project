package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage{
    private Logger logger= Logger.getLogger(getClass());
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/";
    }

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[@href='/create-post']")
    private WebElement buttonCreatePost;


    public HomePage checkIsButtonSignOutVisible(){
        checkIsElementEnabled(buttonSignOut);
        return this;
    }

    //приклад альтернативного способу перевірки видимості елемента
/*    public boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(
                    By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Sign Out Element state: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Sign Out Element is not found");
            return false;
        }
    }*/

    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        //TODO check some unique element on HomePage
        return this;
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public CreatePostPage clickOnButtonCreateNewPost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public HomePage checkIsButtonCreatePostVisible() {
        checkIsElementEnabled(buttonCreatePost);
        return this;
    }


    public HomePage checkIsNotButtonSignOutVisible() {
        checkIsNotElementVisible(buttonSignOut);
        return this;
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (isElementEnabled(buttonSignOut)) {
            logger.info("User is already logged in");
            return this;
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN)
                    .enterTextIntoInputPassword(TestData.VALID_PASSWORD)
                    .clickOnButtonSignIn();
                    checkIsButtonSignOutVisible();
                    logger.info("User was logged in successfully");
        }
        return this;
    }


}
