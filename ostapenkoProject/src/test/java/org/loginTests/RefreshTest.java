package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class RefreshTest extends BaseTest {

    @Test
    public void TC02_checkUserIsNotLoggedAfterRefreshPage() {

        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin("qaauto")
                .enterTextIntoInputPasswort("123456qwerty")
                .refreshPage()
                .clickOnButtonSignIn();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checksButtonSignOutNotVisible();

        logger.info("Refresh test completed successfully");
    }

}
