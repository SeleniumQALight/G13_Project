package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.pages.HomePage;

public class SignOutInTwoTabs extends BaseTest {
    @Test
    public void signOutInTwoTabs() {

        HomePage homePage = pageProvider.getLoginPage()
                .openLoginPageAndFillFormWithValidCred()
                .checkIsRedirectToHomePage();

        homePage.saveMainTabHandle();

        homePage.openNewTabInBrowser();
        homePage.switchToNewTab();

        homePage.getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible();

        homePage.switchToMainTab();
        homePage.getHeaderForLoggedUserElement()
                .clickOnButtonSignOut()
                .checksButtonSignOutNotVisible();

        homePage.switchToNewTab()
                .refreshPage();
        homePage.getHeaderForLoggedUserElement()
                .checksButtonSignOutNotVisible();

        logger.info("'Sign out in two tabs' test completed successfully");
    }
}
