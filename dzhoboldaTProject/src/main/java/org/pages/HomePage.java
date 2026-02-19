package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.pages.elements.HeaderForLoggedUserElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends ParentPage {
    private final Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createNewPostButton;
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public void checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button SignOut is not visible", isButtonSignOutVisible());
        logger.info("Button SignOut is visible");
    }

    public boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element sate: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    public HomePage checkRedirectToHomePage() {
        checkUrl();
        // TODO Check unique element
        return this;
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(createNewPostButton);
        logger.info("Button Create Post was clicked");
        return new CreatePostPage(webDriver);
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.ValidLogin)
                    .enterTextIntoInputPassword(TestData.ValidPassword)
                    .clickOnButtonSignIn();
            checkRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }

    //  геттеры
    public WebElement getButtonSignOut() {
        return buttonSignOut;
    }

    public HomePage openNewTabByJS() {
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        logger.info("New tab was opened via JavaScript");
        return this;
    }

    public HomePage switchToNewTab() {
        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabs.size() - 1)); // переключаемся на последнюю вкладку
        logger.info("Switched to new tab");
        return this;
    }



    public void checkIsButtonSignOutNotVisible() {
        Assert.assertFalse("Button SignOut is visible but should NOT be", isButtonSignOutVisible());
        logger.info("Button SignOut is NOT visible");
    }


}
