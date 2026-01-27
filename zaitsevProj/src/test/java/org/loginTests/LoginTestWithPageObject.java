package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import java.util.Set;

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
    public void invalidLogin() {
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
    public void signOut() {
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

    @Test
    public void checkIsUserLoggedInAfterOpenInNewTab() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible();

        String mainTabHandle = pageProvider.getHomePage().getCurrentTabHandle();
        Set<String> oldTabHandles = pageProvider.getHomePage().getAllTabHandles();
        pageProvider.getHomePage().openNewTab();

        String newTabHandle = pageProvider.getHomePage().switchToNewTab(oldTabHandles);

        pageProvider.getLoginPage()
                .openLoginPage();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible();

        pageProvider.getHomePage().switchToTab(mainTabHandle);

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible();

        pageProvider.getHomePage().switchToTab(newTabHandle);
        pageProvider.getHomePage().closeCurrentTabAndSwitchToTab(mainTabHandle);

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible();
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
