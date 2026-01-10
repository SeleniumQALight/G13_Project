package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

    public class LoginTestWithPageObject extends BaseTest {
        @Test
        public void validLogin(){
            pageProvider.getLoginPage().openLoginPage();
            pageProvider.getLoginPage().enterTextIntoInputLogin("Dzh2399");
            pageProvider.getLoginPage().enterTextIntoInputPassword("Stringi23992399");
            pageProvider.getLoginPage().clickOnButtonSignIn();

            pageProvider.getHomePage().checkIsButtonSignOutVisible();
        }

    }

