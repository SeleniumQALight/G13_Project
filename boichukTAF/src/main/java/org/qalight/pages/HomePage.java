
package org.qalight.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button Sign Out is not visible",
                isElementDisplayed(buttonSignOut));
    }

    public void checkIsButtonSignOutNotVisible() {
        Assert.assertFalse("Button Sign Out should not be visible",
                isElementDisplayed(buttonSignOut));
    }

    public void checkIsButtonCreatePostVisible() {
        Assert.assertTrue("Button Create Post is not visible",
                isElementDisplayed(buttonCreatePost));
    }
}