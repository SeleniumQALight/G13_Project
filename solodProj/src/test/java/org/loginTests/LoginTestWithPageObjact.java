package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObjact extends BaseTest {
    @Test
    public void validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSingIn();

        pageProvider.getHopePage().checkIsButtonSingOutVisible();
    }


}
