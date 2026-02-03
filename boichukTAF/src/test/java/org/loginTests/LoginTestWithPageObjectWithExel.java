package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.pages.utils.ConfigProvider;
import org.pages.utils.ExcelDriver;

import java.io.IOException;
import java.util.Map;

import static org.data.TestData.VALID_LOGIN;
import static org.data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObjectWithExel extends BaseTest {

    @Test
    public void TC02_validLogin() throws IOException {
        Map<String,String> dataForValidLogin = ExcelDriver.getData(
                ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(dataForValidLogin.get("login"))
                .enterTextIntoInputPassword(dataForValidLogin.get("pass"))
                .clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsButtonSignOutVisible();
    }

}
