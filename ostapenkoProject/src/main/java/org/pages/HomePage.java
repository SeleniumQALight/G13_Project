package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createNewPostButton;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checksButtonSignOutVisible() {
        checksElementVisible(buttonSignOut, "Sing Out");
        logger.info("Button SingOut is visible");
    }

    public void checksButtonSignOutNotVisible() {
        checksElementNotVisible(buttonSignOut, "Sign Out");
    }

    public void checksButtonCreatePostVisible() {
        checksElementVisible(buttonCreatePost, "Create Post");
        logger.info("Button CreatePost is visible");
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
}