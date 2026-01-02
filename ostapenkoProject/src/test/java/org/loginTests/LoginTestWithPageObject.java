package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest { // get @before and @After
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPasswort("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().checksButtonSignOutVisible();
        pageProvider.getHomePage().checksButtonCreatePostVisible();
        pageProvider.getLoginPage().checkLoginAndPasswordInputsAreNotVisible();

    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto1");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().checksButtonSignOutNotVisible();
        pageProvider.getHomePage().checksButtonSignInVisible();
        pageProvider.getHomePage().checksInvalidMessageVisible();
    }

}