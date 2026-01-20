package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage{
    private Logger logger= Logger.getLogger(getClass());
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;


    public HomePage checkIsRedirectToHomePage() {
        //TODO Check URL
        //TODO check some unique element on HomePage
        return this;
    }


    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (isElementEnabled(buttonSignOut)) {
            logger.info("User is already logged in");
            return this;
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN)
                    .enterTextIntoInputPassword(TestData.VALID_PASSWORD)
                    .clickOnButtonSignIn()
                    .getHeaderForLoggedUserElement().checkIsButtonSignOutVisible();
                    logger.info("User was logged in successfully");
        }
        return this;
    }


}
