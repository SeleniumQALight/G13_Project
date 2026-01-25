package org.pages.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreatePostPage;
import org.openqa.selenium.NoSuchElementException;
import org.pages.HomePage;
import org.pages.MyProfilePage;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement buttonChat;

    @FindBy(xpath = "//a[@href='/profile/qaauto']")
    private WebElement buttonAvatar;

    Logger logger = Logger.getLogger(getClass());

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isUserLoggedIn() {
        try {
            return buttonSignOut.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public HeaderForLoggedUserElement checksButtonSignOutVisible() {
        checksElementVisible(buttonSignOut, "Sing Out");
        logger.info("Button SingOut is visible");
        return this;
    }

    public HomePage getHomePage() {
        return new HomePage(webDriver);
    }

    public HeaderForLoggedUserElement checksButtonSignOutNotVisible() {
        checksElementNotVisible(buttonSignOut, "Sign Out");
        logger.info("Button SingOut is NOT visible");
        return this;
    }

    public HeaderForLoggedUserElement clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return this;
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public HeaderForLoggedUserElement checksButtonCreatePostVisible() {
        checksElementVisible(buttonCreatePost, "Create Post");
        logger.info("Button CreatePost is visible");
        return this;
    }

    public HeaderForLoggedUserElement checksButtonCreatePostNotVisible() {
        checksElementNotVisible(buttonCreatePost, "Create Post");
        logger.info("Button CreatePost is NOT visible");
        return this;
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public HeaderForLoggedUserElement checksButtonSearchVisible() {
        checksElementVisible(buttonSearch, "Search");
        logger.info("Button Search is visible");
        return this;
    }

    public HeaderForLoggedUserElement checksButtonSearchNotVisible() {
        checksElementNotVisible(buttonSearch, "Search");
        logger.info("Button Search is NOT visible");
        return this;
    }

    public HeaderForLoggedUserElement checksButtonChatVisible() {
        checksElementVisible(buttonChat, "Chat");
        logger.info("Button Chat is visible");
        return this;
    }

    public HeaderForLoggedUserElement checksButtonChatNotVisible() {
        checksElementNotVisible(buttonChat, "Chat");
        logger.info("Button Chat is NOT visible");
        return this;
    }

    public HeaderForLoggedUserElement checksAvatarVisible() {
        checksElementVisible(buttonAvatar, "Avatar");
        logger.info("Button Avatar is visible");
        return this;
    }

    public HeaderForLoggedUserElement checksAvatarNotVisible() {
        checksElementNotVisible(buttonAvatar, "Avatar");
        logger.info("Button Avatar is NOT visible");
        return this;
    }
}
