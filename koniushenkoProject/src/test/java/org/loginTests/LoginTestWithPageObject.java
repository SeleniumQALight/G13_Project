package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoInputPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage().checkIsInputLoginNotVisible();
        pageProvider.getLoginPage().checkIsInputPasswordNotVisible();
    }

    @Test
    public void invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto123");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().checkIsButtonSingInVisible();
        pageProvider.getLoginPage().checkIsTextErrorInvalidDataVisible();
        pageProvider.getHomePage().checkIsButtonSignOutNotVisible();

    }
}
