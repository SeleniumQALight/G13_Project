package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoInputPasswort(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checksButtonSignOutVisible();
        pageProvider.getHomePage().getHeaderForLoggedUserElement().checksButtonCreatePostVisible();
        pageProvider.getLoginPage().checkLoginAndPasswordInputsAreNotVisible();

    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto1");
//        pageProvider.getLoginPage().enterTextIntoInputPasswort("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checksButtonSignOutNotVisible();
        pageProvider.getLoginPage().checksButtonSignInVisible();
        pageProvider.getLoginPage().checksInvalidMessageVisible();
    }

}