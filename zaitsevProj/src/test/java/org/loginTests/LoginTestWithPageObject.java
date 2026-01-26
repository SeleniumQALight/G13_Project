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

        pageProvider.getHomePage().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().checkIsButtonCreatePostVisible();
        pageProvider.getLoginPage().checkIsLoginInputNotVisible();
        pageProvider.getLoginPage().checkIsPasswordInputNotVisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto1");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty"); //is not a necessary step
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsButtonSignOutIsNotVisible();
        pageProvider.getLoginPage().checkIsButtonSignInVisible();
        pageProvider.getLoginPage().checkIsErrorMessageIsVisibleWithText("Invalid username/password.");
    }

    @Test
    public void checkIsUserLoggedInAfterOpenInNewTab() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsButtonSignOutVisible();

        String mainTabHandle = pageProvider.getHomePage().getCurrentTabHandle();

        pageProvider.getHomePage().openNewTab();

        String newTabHandle = pageProvider.getHomePage().switchToNewTab(mainTabHandle);

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().checkIsButtonSignOutVisible();

        pageProvider.getHomePage().switchToTab(mainTabHandle);
        pageProvider.getHomePage().checkIsButtonSignOutVisible();

        pageProvider.getHomePage().switchToTab(newTabHandle);
        pageProvider.getHomePage().closeCurrentTab();
        pageProvider.getHomePage().switchToTab(mainTabHandle);

        pageProvider.getHomePage().checkIsButtonSignOutVisible();

    }

    @Test
    public void checkInputsAreEmptyAfterRefreshPage() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsButtonSignOutIsNotVisible();
    }
}
