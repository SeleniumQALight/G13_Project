package org.loginTests;

import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestsFilter.class)
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

    @Test
    public void validLoginViaTabAndEnter() {

        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLoginUsingActions(VALID_LOGIN_UI)
                .enterTextIntoInputPasswordUsingActions(VALID_PASSWORD_UI)
                .pressEnterKeyOnSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checksButtonSignOutVisible();

        logger.info("Valid login via Tab and Enter test completed successfully");
    }

}