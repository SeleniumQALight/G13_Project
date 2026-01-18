package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputUsername("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickButtonSignIn();

        pageProvider.getHomePage().checkIsButtonSignOutVisible(true);
        pageProvider.getHomePage().checkIsButtonCreatePostVisible(true);
        pageProvider.getLoginPage().checkIsInputLoginVisible(false);
        pageProvider.getLoginPage().checkIsInputPasswordVisible(false);
        pageProvider.getLoginPage().checkIsButtonSignInVisible(false);
        pageProvider.getLoginPage().checkIsInvalidLoginErrorMessageVisible(false);
    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputPassword("invalid");
        pageProvider.getLoginPage().enterTextIntoInputPassword("invalid12");
        pageProvider.getLoginPage().clickButtonSignIn();

        pageProvider.getLoginPage().checkIsInvalidLoginErrorMessageVisible(true);
        pageProvider.getLoginPage().checkIsButtonSignInVisible(true);
        pageProvider.getLoginPage().checkIsInputLoginVisible(true);
        pageProvider.getLoginPage().checkIsInputPasswordVisible(true);
        pageProvider.getHomePage().checkIsButtonSignOutVisible(false);
        pageProvider.getHomePage().checkIsButtonCreatePostVisible(false);
    }
}
