package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    protected String mainTabHandle, newTabHandle;

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        // TODO check some unique element on HomePage
        return this;
    }

    public HomePage openHomePageAndLoginNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (getHeaderForLoggedUserElement().isUserLoggedIn()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
                    .enterTextIntoInputPasswort(TestData.VALID_PASSWORD_UI)
                    .clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }

    public HomePage saveMainTabHandle() {
        mainTabHandle = webDriver.getWindowHandle();
        logger.info("Main tab saved: " + mainTabHandle);
        return this;
    }

    public HomePage openNewTabInBrowser() {
        openNewTab();
        return this;
    }
    public HomePage switchToNewTab() {
        switchToNewTab(mainTabHandle, newTabHandle);
        return this;
    }

    public HomePage switchToMainTab() {
        switchToMainTab(mainTabHandle);
        return this;
    }

    public HomePage closeNewTab (){
        closeNewTab(mainTabHandle);
        return this;
    }

}