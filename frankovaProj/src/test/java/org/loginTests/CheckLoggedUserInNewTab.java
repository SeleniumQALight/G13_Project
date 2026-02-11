package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class CheckLoggedUserInNewTab extends BaseTest {

    @Test
    public void TC07_LoggedUserInNewTab() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn()
                .getHeaderForLoggedUserElement().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().openNewTabInBrowser();
        pageProvider.getLoginPage().switchToTabInBrowser(1);
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().switchToTabInBrowser(0);
        pageProvider.getHeaderForLoggedUserElement().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().closeTabInBrowser(1);
        pageProvider.getLoginPage().switchToTabInBrowser(0);
        pageProvider.getHeaderForLoggedUserElement().checkIsButtonSignOutVisible();

    }
}
