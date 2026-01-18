package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTestIntoInputLogin("qaauto")
                .enterTextIntoInputPassword("123456qwerty")
                .clickOnButtonSignIn();

        pageProvider.getHomePage()
                .checkButtonSignOutIsVisible()
                .checkButtonCreatePostVisible();
    }
    @Test
    public void invalidLogin(){
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTestIntoInputLogin("invalid")
                .enterTextIntoInputPassword("invalid")
                .clickOnButtonSignIn()
                .checkButtonSignInIsVisible()
                .checkMessageInvalidUserOrPassword();

        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTestIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().checkButtonSignOutIsVisible();
    }
}
