package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginInNewTabTest extends BaseTest {

    //перевірка того, що при відкритті сайту в новій вкладці (javaScript-ом)
    //залогіненим юзером ми одразу залогінені. (перевірити, що кнопка SignOut видима)
    // також перевірити що при закритті нової вкладки ми залишаємось залогіненими

    @Test
    public void loginInNewTabTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible()
                .openNewTab()
                .switchToTab(1);
        pageProvider.getLoginPage()
                .openLoginPage();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible()
                .switchToTab(0)
                .getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible()
                .switchToTab(1)
                .closeCurrentTabAndSwitchToMain(0)
                .getHeaderForLoggedUserElement()
                .checksButtonSignOutVisible();
    }
}
