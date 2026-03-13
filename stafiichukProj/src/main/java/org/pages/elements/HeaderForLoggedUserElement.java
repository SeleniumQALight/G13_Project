package org.pages.elements;

import org.junit.Assert;
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

    public boolean isSearchButtonVisible(){
        return isElementDisplayed(buttonSearch);
    }

    public void checkIsSearchButtonVisible(){
        Assert.assertTrue("Search button is not visible", isElementDisplayed(buttonSearch));
        logger.info("Search button is visible");
    }

    public boolean isChatButtonVisible(){
        return isElementDisplayed(buttonChat);
    }

    public void checkIsChatButtonVisible(){
        Assert.assertTrue("Chat button is not visible", isElementDisplayed(buttonChat));
        logger.info("Chat button is visible");
    }

    public boolean isMyProfileButtonVisible(){
        return isElementDisplayed(buttonMyProfile);
    }

    public void checkIsMyProfileButtonVisible(){
        Assert.assertTrue("My Profile is not visible", isElementDisplayed(buttonMyProfile));
        logger.info("My Profile is visible");
    }

    public boolean isCreatePostButtonVisible(){
        return isElementDisplayed(buttonCreatePost);
    }

    public void checkIsCreatePostButtonVisible(){
        Assert.assertTrue("Create Post button is not visible", isElementDisplayed(buttonCreatePost));
        logger.info("Create Post button is visible");
    }

    public boolean isSignOutButtonVisible(){
        return isElementDisplayed(buttonSignOut);
    }

    public void checkIsSignOutButtonVisible(){
        Assert.assertTrue("Sign Out button is not visible", isElementDisplayed(buttonSignOut));
        logger.info("Sign Out button is visible");
    }

    public void clickOnSignOutButton(){
        clickOnElement(buttonSignOut);
    }
}
