package org.pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.*;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//a[contains(@class, 'header-search-icon')]")
    private WebElement iconSearchInput;

    @FindBy(xpath = "//span[contains(@class, 'header-chat-icon')]")
    private WebElement iconChat;

    @FindBy(xpath = "//a//img[@alt=\"My profile\"]")
    private WebElement iconAvatar;

    @FindBy(xpath = ".//a[@href='/create-post']")
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

    public HeaderForLoggedUserElement checkIsIconSearchInputVisible() {
        checkIsElementEnabled(iconSearchInput);
        return this;
    }

    public HeaderForLoggedUserElement checkIsChatIconVisible() {
        checkIsElementEnabled(iconChat);
        return this;
    }

    public HeaderForLoggedUserElement checkIsAvatarIconVisible() {
        checkIsElementEnabled(iconAvatar);
        return this;
    }

    public HeaderForLoggedUserElement checkIsNotIconSearchInputVisible() {
        checkIsNotElementVisible(iconSearchInput);
        return this;
    }

    public HeaderForLoggedUserElement checkIsNotChatIconVisible() {
        checkIsNotElementVisible(iconChat);
        return this;
    }

    public HeaderForLoggedUserElement checkIsNotAvatarIconVisible() {
        checkIsNotElementVisible(iconAvatar);
        return this;
    }

    public HeaderForLoggedUserElement checkIsButtonCreatePostVisible() {
        checkIsElementEnabled(buttonCreatePost);
        return this;
    }

    @Step
    public HeaderForLoggedUserElement checkIsButtonSignOutVisible() {
        checkIsElementEnabled(buttonSignOut);
        return this;
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public HeaderForLoggedUserElement checkIsNotButtonSignOutVisible() {
        checkIsNotElementVisible(buttonSignOut);
        return this;
    }

    public HeaderForLoggedUserElement checkIsNotButtonCreatePostVisible() {
        checkIsNotElementVisible(buttonCreatePost);
        return this;
    }

    public CreatePostPage clickOnButtonCreateNewPost() {
        clickOnElement(buttonCreatePost);
       return new CreatePostPage(webDriver);
    }

}
