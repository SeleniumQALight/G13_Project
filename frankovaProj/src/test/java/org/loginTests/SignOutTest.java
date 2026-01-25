package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void TC03_signOutTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().checkIsIconSearchInputVisible()
                .checkIsChatIconVisible()
                .checkIsAvatarIconVisible()
                .checkIsButtonCreatePostVisible()
                .checkIsButtonSignOutVisible()
                .clickOnButtonSignOut()
                .checkIsRedirectToLoginPage()

                .getHeaderForLoggedUserElement().checkIsNotIconSearchInputVisible()
                .checkIsNotChatIconVisible()
                .checkIsNotAvatarIconVisible()
                .checkIsNotButtonSignOutVisible()
                .checkIsNotButtonCreatePostVisible();

        pageProvider.getLoginPage()
                .checkIsInputLoginVisible()
                .checkIsInputPasswordVisible()
                .checkIsButtonSignInVisible();

    }
}
