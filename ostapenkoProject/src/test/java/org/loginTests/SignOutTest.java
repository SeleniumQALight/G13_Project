package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void SignOut() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement()
                .checksButtonSearchVisible()
                .checksButtonChatVisible()
                .checksAvatarVisible()
                .checksButtonCreatePostVisible();

//                .clickOnButtonSignOut();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonSignOut()
                .checksButtonSearchNotVisible()
                .checksButtonChatNotVisible()
                .checksAvatarNotVisible()
                .checksButtonSignOutNotVisible();

        pageProvider.getLoginPage()
                .checksButtonSignInVisible()
                .checkLoginAndPasswordInputsAreVisible();

        logger.info("Sign Out test completed successfully");

    }
}
