package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void TC03_signOutTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().checkIsIconSearchInputVisible()
                .checkIsChatIconVisible()
                .checkIsAvatarIconVisible()
                .checkIsButtonCreatePostVisible()
                .checkIsButtonSignOutVisible()
                .clickOnButtonSignOut()
                .checkIsRedirectToLoginPage()

                .getHeaderForLoggedUserElement().checkIsNotIconSearchInputVisible()
                .checkIsNotChatIconVisible()
                .checkIsNotAvatarIconVisible()
                .checkIsNotButtonSignOutVisible()
                .checkIsNotButtonCreatePostVisible();

        pageProvider.getLoginPage()
                .checkIsInputLoginVisible()
                .checkIsInputPasswordVisible()
                .checkIsButtonSignInVisible();

    }

    @Test
    public void TC10_signOutInTwoTabsTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderForLoggedUserElement().checkIsButtonSignOutVisible();
        pageProvider.getCommonActionsWithElements().openNewTabInBrowser();
        pageProvider.getCommonActionsWithElements().switchToTabInBrowser(1);
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHeaderForLoggedUserElement().checkIsButtonSignOutVisible();
        pageProvider.getCommonActionsWithElements().switchToTabInBrowser(0);
        pageProvider.getHeaderForLoggedUserElement().clickOnButtonSignOut();
        pageProvider.getHeaderForLoggedUserElement().checkIsNotButtonSignOutVisible();
        pageProvider.getCommonActionsWithElements().switchToTabInBrowser(1);
        pageProvider.getCommonActionsWithElements().refreshPageInBrowser();
        pageProvider.getHeaderForLoggedUserElement().checkIsNotButtonSignOutVisible();

    }
}
