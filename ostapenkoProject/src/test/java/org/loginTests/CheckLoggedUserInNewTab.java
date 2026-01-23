package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CheckLoggedUserInNewTab extends BaseTest {
    @Test
    public void checkLoggedUserInNewTab() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible()
                .getHomePage()
                .openNewTabInBrowser()
                .switchToNewTab()
                .getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible()
                .getHomePage()
                .closeNewTabAndSwitchToMainTab()
                .getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible();

        logger.info("'Check logged user' in new tab test completed successfully");
    }
}
