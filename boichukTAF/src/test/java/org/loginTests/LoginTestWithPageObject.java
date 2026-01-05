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

        // Перевірки після успішного логіну
        pageProvider.getHomePage().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().checkIsButtonCreatePostVisible();
        pageProvider.getHomePage().checkLoginInputsAreNotVisible();
    }

    @Test
    public void invalidLoginChecks() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("abrakadabraLogin");
        pageProvider.getLoginPage().enterTextIntoInputPassword("abrakadabraPassword");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        // Перевірки після неуспішного логіну
        pageProvider.getHomePage().checkIsButtonSignOutNotVisible();
        pageProvider.getHomePage().checkIsButtonSignInVisible();
        pageProvider.getHomePage().checkInvalidLoginMessageIsVisible();
    }
}
