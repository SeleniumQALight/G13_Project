package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSignOutVisible(){
        Assert.assertTrue("Button SignOut is not visible", isElementDisplayed(buttonSignOut));
        logger.info("Button SignOut is visible");
    }

    public void checkIsButtonCreatePostVisible(){
        Assert.assertTrue("Button CreatePost is not visible", isElementDisplayed(buttonCreatePost));
        logger.info("Button CreatePost is visible");
    }

    public void checkIsButtonSignOutIsNotVisible(){
        Assert.assertFalse("Button Sign Out is visible, but should not be", isElementDisplayed(buttonSignOut));
        logger.info("Button Sign Out is not visible");
    }

     boolean isButtonSignOutVisible() {
         try {
             boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
             logger.info("Element state: + " + state);
             return state;
         } catch (Exception e) {
             logger.info("Element is not found");
             return false;
         }
     }
}
