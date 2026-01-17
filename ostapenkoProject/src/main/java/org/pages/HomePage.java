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
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createNewPostButton;

    @FindBy(xpath = "//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement buttonChat;

    @FindBy(xpath = "//a[@href='/profile/qaauto']")
    private WebElement buttonAvatar;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public HomePage checksButtonSignOutVisible() {
        checksElementVisible(buttonSignOut, "Sing Out");
        logger.info("Button SingOut is visible");
        return this;
    }

    public HomePage checksButtonSignOutNotVisible() {
        checksElementNotVisible(buttonSignOut, "Sign Out");
        logger.info("Button SingOut is NOT visible");
        return this;
    }

    public HomePage checksButtonCreatePostVisible() {
        checksElementVisible(buttonCreatePost, "Create Post");
        logger.info("Button CreatePost is visible");
        return this;
    }

    public HomePage checksButtonCreatePostNotVisible() {
        checksElementNotVisible(buttonCreatePost, "Create Post");
        logger.info("Button CreatePost is NOT visible");
        return this;
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

    public HomePage openHomePageAndLoginNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (buttonSignOut.isDisplayed()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
                    .enterTextIntoInputPasswort(TestData.VALID_PASSWORD_UI)
                    .clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }


    public HomePage checksButtonSearchVisible() {
        checksElementVisible(buttonSearch, "Search");
        logger.info("Button Search is visible");
        return this;
    }

    public HomePage checksButtonSearchNotVisible() {
        checksElementNotVisible(buttonSearch, "Search");
        logger.info("Button Search is NOT visible");
        return this;
    }

    public HomePage checksButtonChatVisible() {
        checksElementVisible(buttonChat, "Chat");
        logger.info("Button Chat is visible");
        return this;
    }

    public HomePage checksButtonChatNotVisible() {
        checksElementNotVisible(buttonChat, "Chat");
        logger.info("Button Chat is NOT visible");
        return this;
    }

    public HomePage checksAvatarVisible() {
        checksElementVisible(buttonAvatar, "Avatar");
        logger.info("Button Avatar is visible");
        return this;
    }

    public HomePage checksAvatarNotVisible() {
        checksElementNotVisible(buttonAvatar, "Avatar");
        logger.info("Button Avatar is NOT visible");
        return this;
    }

    public void clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
    }

}