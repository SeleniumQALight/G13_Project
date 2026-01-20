package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = ".//form[@action='/logout']//button")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonCreatePostVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue("Button Create Post isn't visible", isElementVisible(buttonCreatePost));
            logger.info("Button Create Post is visible");
        } else {
            Assert.assertFalse("Button Sign Out is visible", isElementVisible(buttonCreatePost));
            logger.info("Button Create Post isn't visible");
        }
    }

    public void checkIsButtonSignOutVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue("Button Sign Out is not visible", isElementVisible(buttonSignOut));
            logger.info("Button Sign Out is visible");
        } else {
            Assert.assertFalse("Button Sign Out is visible", isElementVisible(buttonSignOut));
            logger.info("Button Sign Out isn't visible");
        }

    }

}
