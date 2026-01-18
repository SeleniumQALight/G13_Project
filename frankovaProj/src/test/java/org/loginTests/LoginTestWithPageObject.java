package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();

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
