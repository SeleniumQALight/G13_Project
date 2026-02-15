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
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
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
                .getLoginPage()
                .checkIsLoginInputVisible()
                .checkIsPasswordInputVisible()
                .checkIsButtonSignInVisible();
    }
}
