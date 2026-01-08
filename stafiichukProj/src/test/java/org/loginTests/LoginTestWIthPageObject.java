package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWIthPageObject extends BaseTest {

    @Test
    public void validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsButtonSignOutVisible();

        pageProvider.getHomePage().checkIsButtonCreatePostVisible();

        Assert.assertFalse("Input Login is visible",
                pageProvider.getLoginPage().isInputLoginVisible());
        Assert.assertFalse("Input Password is visible",
                pageProvider.getLoginPage().isInputPasswordVisible());
    }

    @Test
    public void invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto2");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertFalse("Sign Out button is visible",
                pageProvider.getHomePage().isButtonSignOutVisible());
        Assert.assertTrue("Sign In button is not visible",
                pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertTrue("Error message is not displayed", pageProvider.getLoginPage().isErrorMessageVisible());

    }
}
