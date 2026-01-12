package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutVisible(){
        return isElementDisplayed(buttonSignOut);
    }

    public void checkIsButtonSignOutVisible(){
        Assert.assertTrue("Button SignOut is not visible", isButtonSignOutVisible());
        logger.info("Button SignOut is visible");

    }
    public void checkIsButtonCreatePostVisible(){
        Assert.assertTrue("Button Create Post is not visible", isElementDisplayed(buttonCreatePost));
    }

}
