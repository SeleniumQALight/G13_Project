package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void SignOut() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillFormWithValidCred()
                .checkIsRedirectToHomePage()
                .checksButtonSearchVisible()
                .checksButtonChatVisible()
                .checksAvatarVisible()
                .checksButtonSignOutVisible()
                .checksButtonCreatePostVisible()
                .clickOnButtonSignOut();

        pageProvider.getHomePage()
                .checksButtonSearchNotVisible()
                .checksButtonChatNotVisible()
                .checksAvatarNotVisible()
                .checksButtonCreatePostNotVisible()
                .checksButtonSignOutNotVisible();

        pageProvider.getLoginPage()
                .checksButtonSignInVisible()
                .checkLoginAndPasswordInputsAreVisible();

        logger.info("Sign Out test completed successfully");

    }
}
