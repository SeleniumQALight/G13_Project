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
    @FindBy(xpath ="//input[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath ="//input[@placeholder='Password']")
    private WebElement inputPassword;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSighOutVisible() {
        Assert.assertTrue("Button SignOut is not visible", isWebElementIsVisible(buttonSignOut));
        logger.info("Button SignOut is visible");
    }
    public void checkButtonSignOutVisible(){
        Assert.assertTrue("Button Sign Out is not visible",isWebElementIsVisible(buttonSignOut));
        logger.info("Button Sign Out is visible");
    }
    public void checkInputPasswordFieldIsNotVisible(){
        Assert.assertFalse("Input Password Field is visible",isWebElementIsVisible(inputPassword));
        logger.info("Input Password Field is visible");
    }
    public void checkInputLoginFieldIsNotVisible(){
        Assert.assertFalse("Input Login Field is visible",isWebElementIsVisible(inputLogin));
        logger.info("Input Login Field is not visible");
    }
    public void checkButtonCreatePostVisible(){
        Assert.assertTrue("Button Create post is not visible",isWebElementIsVisible(buttonCreatePost));
        logger.info("Button Create post is visible");
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
