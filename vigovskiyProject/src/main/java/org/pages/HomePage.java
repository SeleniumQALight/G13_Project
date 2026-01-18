package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = "//button[text()='Sign Out']")
    WebElement buttonSignOut;
    @FindBy(xpath = "//a[@href=\"/create-post\"]")
    WebElement buttonCreatePost;
    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createNewPostButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkButtonSignOutIsVisible() {
        Assert.assertTrue("Button Sign Out is not visible", isWebElementIsVisible(buttonSignOut));
        logger.info("Button Sign Out is visible");
        return this;
    }

    public void checkButtonCreatePostVisible() {
        Assert.assertTrue("Button Create post is not visible", isWebElementIsVisible(buttonCreatePost));
        logger.info("Button Create post is visible");
    }


    public HomePage checkIsRedirectToHomePage() {
        //TODO check URL
        //TODO check some unique element on HomePage
        return this;
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(createNewPostButton);
        return new CreatePostPage(webDriver);
    }
//    public boolean isButtonSighOutIsVisible() {
//        try {
//            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            logger.info("Element state: " + state);
//            return state;
//        } catch (Exception e) {
//            logger.info("Element is not found");
//            return false;
//        }
//
//    }
}
