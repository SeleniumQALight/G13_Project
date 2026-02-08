package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.pages.HomePage;

public class CheckLoggedUserInNewTab extends BaseTest {
    @Test
    public void checkLoggedUserInNewTab() {

        HomePage homePage = pageProvider.getLoginPage()
                .openLoginPageAndFillFormWithValidCred()
                .checkIsRedirectToHomePage();

        homePage.getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible();


        homePage.openNewTabInBrowser();
        homePage.switchToTabByInt(1) // switch to new tab
                .getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible();


        homePage.switchToTabByInt(0); // switch back to main tab
        homePage.getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible();


        homePage.closeTabByInt(1); // close new tab
        homePage.switchToTabByInt(0).checkIsRedirectToHomePage();
        logger.info("'Check logged user' in new tab test completed successfully");
    }
}
