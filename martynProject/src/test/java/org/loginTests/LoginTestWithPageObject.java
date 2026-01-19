package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;


public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputUsername(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible(true)
                .checkIsButtonCreatePostVisible(true);
        pageProvider.getLoginPage().checkIsInputLoginVisible(false)
                .checkIsInputPasswordVisible(false)
                .checkIsButtonSignInVisible(false)
                .checkIsInvalidLoginErrorMessageVisible(false);
    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputPassword("invalid")
                .enterTextIntoInputPassword("invalid12")
                .clickButtonSignIn();

        pageProvider.getLoginPage().checkIsInvalidLoginErrorMessageVisible(true)
                .checkIsButtonSignInVisible(true)
                .checkIsInputLoginVisible(true)
                .checkIsInputPasswordVisible(true);
        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible(false)
                .checkIsButtonCreatePostVisible(false);
    }
}
