package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement textInvalidMessage;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checksButtonSignOutVisible() {
        Assert.assertTrue("Button SingOut is not visible", checksElementVisible(buttonSignOut));
        logger.info("Button SingOut is visible");
    }

    public void checksButtonCreatePostVisible() {
        Assert.assertTrue("Button CreatePost is not visible", checksElementVisible(buttonCreatePost));
        logger.info("Button CreatePost is visible");
    }

    public void checksButtonSignInVisible() {
        Assert.assertTrue("Button SingIn is not visible", checksElementVisible(buttonSignIn));
        logger.info("Button SingIn is visible");
    }

    public void checksInvalidMessageVisible() {
        Assert.assertTrue("Validation message is NOT visible", checksElementVisible(textInvalidMessage));
        logger.info("Text InvalidMessage is visible");
    }

    public void checksButtonSignOutNotVisible() {
        Assert.assertFalse("Button SignOut is visible, but should NOT be", checksElementVisible(buttonSignOut));
        logger.info("Button SignOut is NOT visible");
    }

}