package org.pages.commonElements;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreatePostPage;
import org.pages.ProfilePage;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {

    @FindBy(xpath = ".//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//form[@action='/logout']//button")
    private WebElement buttonSignOut;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public ProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new ProfilePage(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public HeaderForLoggedUserElement checkIsButtonCreatePostVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue("Button Create Post isn't visible", isElementVisible(buttonCreatePost));
            logger.info("Button Create Post is visible");
        } else {
            Assert.assertFalse("Button Sign Out is visible", isElementVisible(buttonCreatePost));
            logger.info("Button Create Post isn't visible");
        }
        return this;
    }

    public HeaderForLoggedUserElement checkIsButtonSignOutVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue("Button Sign Out is not visible", isElementVisible(buttonSignOut));
            logger.info("Button Sign Out is visible");
        } else {
            Assert.assertFalse("Button Sign Out is visible", isElementVisible(buttonSignOut));
            logger.info("Button Sign Out isn't visible");
        }
        return this;

    }
}
