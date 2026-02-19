package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void TC_signOut() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithVailidCred();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkHeaderElementsVisible()
                .clickOnButtonSignOut();

        pageProvider.getLoginPage()
                .checkLoginInputVisible()
                .checkPasswordInputVisible()
                .checkSignInButtonVisible();
    }

}
