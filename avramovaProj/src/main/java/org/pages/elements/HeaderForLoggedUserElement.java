package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.MyProfilePage;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement buttonChat;

    @FindBy(xpath = "//a[text()='Create Post']")   
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public HeaderForLoggedUserElement checksButtonSearchVisible() {
        checksElementVisible(buttonSearch, "Search");
        return this;
    }

    public HeaderForLoggedUserElement checksButtonChatVisible() {
        checksElementVisible(buttonChat, "Chat");
        return this;
    }

    public HeaderForLoggedUserElement checksAvatarVisible() {
        checksElementVisible(buttonMyProfile, "Avatar");
        return this;
    }

    public HeaderForLoggedUserElement checksButtonCreatePostVisible() {
        checksElementVisible(buttonCreatePost, "Create Post");
        return this;
    }

    public HeaderForLoggedUserElement checksButtonSignOutVisible() {
        checksElementVisible(buttonSignOut, "Sign Out");
        return this;
    }

    //написати метод на натискання кнопки Sign Out
    public HeaderForLoggedUserElement clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return this;
    }

    public HeaderForLoggedUserElement checksButtonSearchNotVisible() {
        checksElementNotVisible(buttonSearch, "Search");
        return this;
    }

    public HeaderForLoggedUserElement checksButtonChatNotVisible() {
        checksElementNotVisible(buttonChat, "Chat");
        return this;
    }

    public HeaderForLoggedUserElement checksAvatarNotVisible() {
        checksElementNotVisible(buttonMyProfile, "Avatar");
        return this;
    }

    public HeaderForLoggedUserElement checksButtonCreatePostNotVisible() {
        checksElementNotVisible(buttonCreatePost, "Create Post");
        return this;
    }

    public HeaderForLoggedUserElement checksButtonSignOutNotVisible() {
        checksElementNotVisible(buttonSignOut, "Sign Out");
        return this;
    }
}
