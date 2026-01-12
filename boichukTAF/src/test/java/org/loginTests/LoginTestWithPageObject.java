package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterLogin("qaauto");
        pageProvider.getLoginPage().enterPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnSignInButton();

        pageProvider.getHomePage().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage().checkLoginInputsAreNotVisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterLogin("abrakadabraLogin");
        pageProvider.getLoginPage().enterPassword("abrakadabraPassword");
        pageProvider.getLoginPage().clickOnSignInButton();

        pageProvider.getHomePage().checkIsButtonSignOutNotVisible();
        pageProvider.getLoginPage().checkIsSignInButtonVisible();
        pageProvider.getLoginPage().checkInvalidLoginMessageIsVisible();
    }
}
