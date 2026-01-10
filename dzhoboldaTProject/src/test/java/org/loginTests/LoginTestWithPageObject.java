package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.ValidLogin;
import static org.data.TestData.ValidPassword;

public class LoginTestWithPageObject extends BaseTest {
        @Test
        public void validLogin(){
            pageProvider.getLoginPage()
                    .openLoginPage()
                    .enterTextIntoInputLogin(ValidLogin)
                    .enterTextIntoInputPassword(ValidPassword)
            .clickOnButtonSignIn();

            pageProvider.getHomePage().checkIsButtonSignOutVisible();
        }

    }

