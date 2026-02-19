package org.pages.elements;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElement;
import org.pages.LoginPage;
import org.pages.MyProfilePage;

public class HeaderForLoggedUserElement extends CommonActionsWithElement {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//a[@href='#']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-placement='bottom']")
    private WebElement buttonChat;

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createNewPostButton;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;



    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }


    public HeaderForLoggedUserElement checkIsButtonSearchVisible() {
        Assert.assertTrue("Button Search is not visible", isElementDisplayed(buttonSearch));
        logger.info("Button Search is visible");
        return this;
    }

    public HeaderForLoggedUserElement checkIsButtonChatVissible() {
        Assert.assertTrue("Button Chat is not visible", isElementDisplayed(buttonChat));
        logger.info("Button Chat is visible");
        return this;
    }

    public HeaderForLoggedUserElement checkIsButtonMyProfileVisible() {
        Assert.assertTrue("Button MyProfile is not visible", isElementDisplayed(buttonMyProfile));
        logger.info("Button MyProfile is visible");
        return this;
    }
    @Step
    public HeaderForLoggedUserElement checkIsButtonSignOutVisible(){
        Assert.assertTrue("Button SignOut is not visible", isElementDisplayed(buttonSignOut));
        logger.info("Button SignOut is visible");
        return this;
    }
    @Step
    public HeaderForLoggedUserElement checkIsButtonCreatePostVisible(){
        Assert.assertTrue("Button CreatePost is not visible", isElementDisplayed(createNewPostButton));
        logger.info("Button CreatePost is visible");
        return this;
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public HeaderForLoggedUserElement checkIsButtonSearchIsNotVisible() {
        Assert.assertFalse("Button Search is visible",isElementDisplayed(buttonSearch));
        logger.info("Button Search is not visible");
        return this;
    }

    public HeaderForLoggedUserElement checkIsButtonChatIsNotVissible() {
        Assert.assertFalse("Button Chat is visible",isElementDisplayed(buttonChat));
        logger.info("Button Chat is not visible");
        return this;
    }

    public HeaderForLoggedUserElement checkIsButtonMyProfileIsNotVisible() {
        Assert.assertFalse("Button MyProfile is visible",isElementDisplayed(buttonMyProfile));
        logger.info("Button MyProfile is not visible");
        return this;
    }

    public HeaderForLoggedUserElement checkIsButtonCreatePostIsNotVisible() {
        Assert.assertFalse("Button CreatePost is visible",isElementDisplayed(createNewPostButton));
        logger.info("Button CreatePost is not visible");
        return this;
    }

    public HeaderForLoggedUserElement checkIsButtonSignOutIsNotVisible() {
        Assert.assertFalse("Button SignOut is visible",isElementDisplayed(buttonSignOut));
        logger.info("Button SignOut is not visible");
        return this;
    }
}
