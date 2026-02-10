package org.loginTests;

import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.ValidLogin;
import static org.data.TestData.ValidPassword;

public class LoginTestWithPageObject extends BaseTest {
        @Test
        @Category(SmokeTestsFilter.class)
        public void validLogin(){
            pageProvider.getLoginPage()
                    .openLoginPage()
                    .enterTextIntoInputLogin(ValidLogin)
                    .enterTextIntoInputPassword(ValidPassword)
            .clickOnButtonSignIn();

            pageProvider.getHomePage().checkIsButtonSignOutVisible();
        }

    }

