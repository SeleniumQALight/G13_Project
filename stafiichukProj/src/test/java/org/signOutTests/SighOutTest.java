package org.signOutTests;

import org.baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class SighOutTest extends BaseTest {

    @Test
    public void signOutTest() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsSearchButtonVisible();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsChatButtonVisible();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsMyProfileButtonVisible();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsCreatePostButtonVisible();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsSignOutButtonVisible();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .clickOnSignOutButton();

        Assert.assertFalse(pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .isSearchButtonVisible());

        Assert.assertFalse(pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .isChatButtonVisible());

        Assert.assertFalse(pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .isMyProfileButtonVisible());

        Assert.assertFalse(pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .isCreatePostButtonVisible());

        Assert.assertFalse(pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .isSignOutButtonVisible());

        Assert.assertTrue(pageProvider.getLoginPage().isInputLoginVisible());
        Assert.assertTrue(pageProvider.getLoginPage().isInputPasswordVisible());
        Assert.assertTrue(pageProvider.getLoginPage().isButtonSignInVisible());

    }
}