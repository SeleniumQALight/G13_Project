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
        pageProvider.getCommonActionsWithElements().openNewTabInBrowser();
        pageProvider.getCommonActionsWithElements().switchToTabInBrowser(1);
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkIsButtonSignOutVisible();
        pageProvider.getCommonActionsWithElements().switchToTabInBrowser(0);
        pageProvider.getHeaderForLoggedUserElement().checkIsButtonSignOutVisible();
        pageProvider.getCommonActionsWithElements().closeTabInBrowser(1);
        pageProvider.getCommonActionsWithElements().switchToTabInBrowser(0);
        pageProvider.getHeaderForLoggedUserElement().checkIsButtonSignOutVisible();

    }
}
