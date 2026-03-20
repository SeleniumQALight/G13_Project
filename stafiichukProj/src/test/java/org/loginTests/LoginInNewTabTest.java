package org.loginTests;
import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginInNewTabTest extends BaseTest {

    @Test
    public void loginInNewTabTest() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderForLoggedUserElement()
                .checkIsSignOutButtonVisible();

        pageProvider.getHomePage()
                .openNewTabWithJS()
                .switchToNewTab();

        pageProvider.getLoginPage().openLoginPage();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsSignOutButtonVisible();

        pageProvider.getHomePage()
                .switchToMainTab()
                .getHeaderForLoggedUserElement()
                .checkIsSignOutButtonVisible();

        pageProvider.getHomePage()
                .closeCurrentTabAndSwitchToMain()
                .getHeaderForLoggedUserElement()
                .checkIsSignOutButtonVisible();
    }
}
