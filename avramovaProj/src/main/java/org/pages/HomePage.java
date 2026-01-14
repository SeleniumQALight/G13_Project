package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{
//    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createNewPostButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible(){
        return isElementDisplayed(buttonSignOut);
    }

    public void checkIsButtonSignOutVisible(){
        Assert.assertTrue("Button SignOut is not visible", isButtonSignOutVisible());
    }

    public void checkIsButtonCreatePostVisible(){
        Assert.assertTrue("Button Create Post is not visible", isElementDisplayed(buttonCreatePost));
    }

    public void checkIsButtonSignOutNotVisible(){
        Assert.assertFalse("Button SignOut is visible", isButtonSignOutVisible());
    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO add check URL
        //TODO check some unique element on HomePage
        return this;
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(createNewPostButton);
        return new CreatePostPage(webDriver);
    }
}
