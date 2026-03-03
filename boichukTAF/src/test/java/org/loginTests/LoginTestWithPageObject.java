
package org.loginTests;

import io.qameta.allure.*;
import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;


@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    public void TC02_validLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsButtonSignOutVisible();
    }

    @Test
    public void TC03_invalidLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin("abrakadabraLogin")
                .enterTextIntoInputPassword("abrakadabraPassword")
                .clickOnButtonSignIn();

        pageProvider.getLoginPage().checkInvalidLoginMessageIsVisible();
        pageProvider.getHomePage().checkIsButtonSignOutNotVisible();
        pageProvider.getLoginPage().checkIsSignInButtonVisible();
    }
}
