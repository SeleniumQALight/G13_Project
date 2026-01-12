package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{
    private Logger logger= Logger.getLogger(getClass());
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[@href='/create-post']")
    private WebElement buttonCreatePost;


    public void checkIsButtonSignOutVisible(){
        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        logger.info("Button Sign Out is visible");
    }

    public boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(
                    By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Sign Out Element state: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Sign Out Element is not found");
            return false;
        }
    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO Check URL
        //TODO check some unique element on HomePage
        return this;
    }

    public CreatePostPage clickOnButtonCreateNewPost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
