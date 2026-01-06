package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage();

        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage().checkIsNotInputLoginVisible();
        pageProvider.getLoginPage().checkIsNotInputPasswordVisible();
    }

    @Test
    public void invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();

        pageProvider.getLoginPage().enterTextIntoInputLogin("invalid_login");
        pageProvider.getLoginPage().enterTextIntoInputPassword("invalid_password");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getLoginPage().checkIsButtonSignInVisible();
        pageProvider.getHomePage().checkIsNotButtonSignOutVisible();
        pageProvider.getLoginPage().checkIsErrorMessageInvalidCredVisible();
    }
}
