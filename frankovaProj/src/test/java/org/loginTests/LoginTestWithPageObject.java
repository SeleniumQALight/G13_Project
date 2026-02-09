package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void TC01_validLogin() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn()
        .getHeaderForLoggedUserElement().checkIsButtonSignOutVisible()
       .checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage().checkIsNotInputLoginVisible();
        pageProvider.getLoginPage().checkIsNotInputPasswordVisible();
    }

    @Test
    public void TC02_invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();

        pageProvider.getLoginPage().enterTextIntoInputLogin("invalid_login");
        pageProvider.getLoginPage().enterTextIntoInputPassword("invalid_password");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getLoginPage().checkIsButtonSignInVisible();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().checkIsNotButtonSignOutVisible();
        pageProvider.getLoginPage().checkIsErrorMessageInvalidCredVisible();
    }

    @Test
    public void TC09_validLoginWithTabAndEnter(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getCommonActionsWithElements().pressTabKeyOnKeyboard();
        pageProvider.getCommonActionsWithElements().pressTabKeyOnKeyboard();
        pageProvider.getCommonActionsWithElements().enterTextInInputWithActions(VALID_LOGIN);
        pageProvider.getCommonActionsWithElements().pressTabKeyOnKeyboard();
        pageProvider.getCommonActionsWithElements().enterTextInInputWithActions(VALID_PASSWORD);
        pageProvider.getCommonActionsWithElements().pressEnterKeyOnKeyboard();
        pageProvider.getHeaderForLoggedUserElement().checkIsButtonSignOutVisible();
    }
}
