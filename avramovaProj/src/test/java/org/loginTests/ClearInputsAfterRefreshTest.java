package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class ClearInputsAfterRefreshTest extends BaseTest {

    //Написати тест на перевірку що при рефреші сторінки введені данні в інпути "зникають" .
    // Наприклад так - вводимо данні , рефрешим сторінку і перевіряємо що не залогінилися
    //Steps:
    // 1. Open login page
    // 2. Enter 'qaauto' login into input Login
    // 3. Enter '123456qwerty' password into input Password
    // 4. Refresh page
    // 5. Click on button SingIn
    // 6. Check that button SignOut is not visible

    @Test
    public void clearInputsAfterRefresh() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoInputPassword(VALID_PASSWORD_UI)
                .refreshPage()
                .clickOnButtonSignIn();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checksButtonSignOutNotVisible();
    }
}
