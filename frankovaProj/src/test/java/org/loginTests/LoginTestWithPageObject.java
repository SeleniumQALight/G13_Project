package org.loginTests;

import io.qameta.allure.*;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

@Epic("Allure examples")
@Feature("Junit 4 support")

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestsFilter.class)
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123") //атачим лінки на баги
    @Issue("432")
    @Story("Base support for bdd annotations")
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
}
