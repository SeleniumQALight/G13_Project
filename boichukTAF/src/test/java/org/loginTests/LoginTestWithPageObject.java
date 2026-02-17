package org.loginTests;

import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Category(SmokeTestsFilter.class)
    public void TC02_validLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsButtonSignOutVisible();
    }

    @Test
    public void TC03_invalidLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin("abrakadabraLogin")
                .enterTextIntoInputPassword("abrakadabraPassword")
                .clickOnButtonSignIn();

        pageProvider.getLoginPage().checkInvalidLoginMessageIsVisible();
        pageProvider.getHomePage().checkIsButtonSignOutNotVisible();
        pageProvider.getLoginPage().checkIsSignInButtonVisible();
    }
}
