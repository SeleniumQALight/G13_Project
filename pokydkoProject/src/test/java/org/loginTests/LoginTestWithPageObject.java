package org.loginTests;

import io.qameta.allure.*;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

@Epic("Allure examples")
@Feature("JUnit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestsFilter.class)
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    public void TC03_validLogin() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoInputPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsButtonSignOutVisible();
    }

    @Test
    public void TC044_validLogin() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoInputPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsButtonSignOutVisible();
    }
}
