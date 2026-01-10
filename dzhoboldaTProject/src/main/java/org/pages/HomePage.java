package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage  extends ParentPage{
    private final Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createNewPostButton;

    private WebElement signOutButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void checkIsButtonSignOutVisible(){
        Assert.assertTrue("Button SignOut is not visible", isButtonSignOutVisible());
        logger.info("Button SignOut is visible");
    }

    public boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element sate: " + state);
            return state;
        }catch (Exception e){
            logger.info("Element is not found");
            return false;
        }
    }
    public HomePage checkRedirectToHomePage(){
        // TODO Check URL
        // TODO Check unique element
        return this;
    }
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(createNewPostButton);
        logger.info("Button Create Post was clicked");
        return new CreatePostPage(webDriver);
    }
}
