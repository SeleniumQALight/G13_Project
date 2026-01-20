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

        pageProvider.getHomePage()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible()
                .checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage().checkIsLoginInputNotVisible();
        pageProvider.getLoginPage().checkIsPasswordInputNotVisible();
    }

    @Test
    public void invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto1");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty"); //is not a necessary step
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsButtonSignOutIsNotVisible();
        pageProvider.getLoginPage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignInVisible();
        pageProvider.getLoginPage().checkIsErrorMessageIsVisibleWithText("Invalid username/password.");
    }

    @Test
    public void signOut(){
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSearchVisible()
                .checkIsButtonChatVissible()
                .checkIsButtonMyProfileVisible()
                .checkIsButtonCreatePostVisible()
                .checkIsButtonSignOutVisible()
                .clickOnButtonSignOut()
                .checkIsRedirectToLoginPage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSearchIsNotVisible()
                .checkIsButtonChatIsNotVissible()
                .checkIsButtonMyProfileIsNotVisible()
                .checkIsButtonCreatePostIsNotVisible()
                .checkIsButtonSignOutIsNotVisible()
                .checkIsLoginInputVisible()
                .checkIsPasswordInputVisible()
                .checkIsButtonSignInVisible();
    }
}
