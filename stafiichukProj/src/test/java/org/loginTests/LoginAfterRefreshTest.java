package org.loginTests;
import org.baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginAfterRefreshTest extends BaseTest {

    @Test
    public void loginAfterRefreshTest() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin("qaauto")
                .enterTextIntoInputPassword("123456qwerty")
                .refreshPage();

        pageProvider.getLoginPage()
                .clickOnButtonSignIn();

        Assert.assertFalse("Sign Out button is visible after refresh page",
                pageProvider.getHomePage().isButtonSignOutVisible()
        );
    }
}
