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

        homePage.getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible();


        homePage.openNewTabInBrowser();
        homePage.switchToTabByInt(1) // switch to new tab
                .getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible(); // check 'sign out' button is visible in new tab

        homePage.switchToTabByInt(0); // switch back to main tab
        homePage.getHeaderForLoggedUserElement()
                .clickOnButtonSignOut() // sign out in main tab
                .checksButtonSignOutNotVisible();

        homePage.switchToTabByInt(1)
                        .refreshPage();

        homePage.getHeaderForLoggedUserElement()
                .checksButtonSignOutNotVisible();

        logger.info("'Sign out in two tabs' test completed successfully");


    }
}
