package org.loginTests;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;

import static org.data.TestData.VALID_PASSWORD;

public class InputsAreCleanAfterPageRefreshingTest extends BaseTest {

    @Test
    public void TC08_inputsAreCleanAfterPageRefreshing() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(TestData.VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.getLoginPage().refreshPageInBrowser();
        pageProvider.getLoginPage().clickOnButtonSignIn()
                .getHeaderForLoggedUserElement().checkIsNotButtonSignOutVisible();

    }

}
