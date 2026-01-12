package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObjact extends BaseTest {
    @Test
    public void validLogin(){
        pageProvider.getLoginPage().openLoginPage()
                .enterTextInputLogin(VALID_LOGIN_UI)
                .enterTextInputPassword(VALID_PASSWORD_UI)
                .clickOnButtonSingIn();


        pageProvider.getHopePage().checkIsButtonSingOutVisible();
    }



}
