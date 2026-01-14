package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage  extends ParentPage{
    private Logger logger = Logger.getLogger(getClass());
    // Элементы страницы
    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createNewPostButton;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement signOutButton;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement signInButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this); // инициализация элементов через @FindBy
    }

    // Сигнатура метода остаётся, но используем метод из CommonActionsWithElements
    public boolean isButtonSignOutVisible() {
        boolean state = isElementDisplayed(signOutButton);
        logger.info("Button Sign Out displayed: " + state);
        return state;
    }

    public boolean isButtonSignInDisplayed() {
        boolean state = isElementDisplayed(signInButton);
        logger.info("Button Sign In displayed: " + state);
        return state;
    }

    public boolean isButtonCreatePostDisplayed() {
        boolean state = isElementDisplayed(createNewPostButton);
        logger.info("Button Create Post displayed: " + state);
        return state;
    }

    // Сохраняем старый метод
    public void checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button SignOut is not visible", isButtonSignOutVisible());
        logger.info("Button SignOut is visible");
    }

    public HomePage checkRedirectToHomePage() {
        // TODO Check URL
        // TODO Check unique element
        return this;
    }

//    public CreatePostPage clickOnButtonCreatePost() {
//        clickOnElement(createNewPostButton);
//        logger.info("Button Create Post was clicked");
//        return new CreatePostPage(webDriver);
//    }


//    public HomePage(WebDriver webDriver) {
//        super(webDriver);
//    }
//
//    public void checkIsButtonSignOutVisible(){
//        Assert.assertTrue("Button SignOut is not visible", isButtonSignOutVisible());
//        logger.info("Button SignOut is visible");
//    }
//
//    public boolean isButtonSignOutVisible() {
//        try {
//            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            logger.info("Element sate: " + state);
//            return state;
//        }catch (Exception e){
//            logger.info("Element is not found");
//            return false;
//        }
//    }
}
