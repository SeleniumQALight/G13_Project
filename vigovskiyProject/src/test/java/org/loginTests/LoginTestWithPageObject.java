package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTestIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();
       // pageProvider.getHomePage().checkIsButtonSighOutVisible();
        pageProvider.getHomePage().checkButtonSignOutVisible();
        pageProvider.getHomePage().checkButtonCreatePostVisible();
        pageProvider.getHomePage().checkInputLoginFieldIsNotVisible();
        pageProvider.getHomePage().checkInputPasswordFieldIsNotVisible();
    }
    @Test
    public void invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTestIntoInputLogin("invalid");
        pageProvider.getLoginPage().enterTextIntoInputPassword("invalid");
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().checkButtonSignInIsVisible();
        pageProvider.getLoginPage().checkMessageInvalidUserOrPassword();
        pageProvider.getLoginPage().checkButtonSignOutIsNotVisible();
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTestIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsButtonSighOutVisible();
    }
}
