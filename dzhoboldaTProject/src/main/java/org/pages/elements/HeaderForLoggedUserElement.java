package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.MyProfilePage;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement avatar;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new  MyProfilePage(webDriver);
    }

    public HeaderForLoggedUserElement clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return this;
    }

    public HeaderForLoggedUserElement checkHeaderElementsVisible() {
        checkIsElementDisplayed(buttonSignOut);
        checkIsElementDisplayed(buttonCreatePost);
        checkIsElementDisplayed(avatar);
        return this;
    }

    public HeaderForLoggedUserElement checkHeaderElementsNotVisible() {
        checkIsElementNotDisplayed(buttonSignOut);
        checkIsElementNotDisplayed(buttonCreatePost);
        checkIsElementNotDisplayed(avatar);
        return this;
    }



}
