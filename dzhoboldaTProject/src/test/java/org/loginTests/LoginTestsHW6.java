package org.loginTests;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.pages.LoginPage;
import org.pages.elements.HeaderForLoggedUserElement;

public class LoginTestsHW6 extends BaseTest {
    @Test
    public void testLoginInNewTab(){
        // Шаг 1-2: Открываем логин страницу и логинимся
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithVailidCred();

        // Шаг 3: Проверяем, что кнопка SignOut видна на основном табе
        pageProvider.getHomePage().checkIsButtonSignOutVisible();

        // Шаг 4-5: Открываем новую вкладку через JS и переключаемся на неё
        pageProvider.getHomePage()
                .openNewTabByJS()
                .switchToNewTab();

        // Шаг 6: В новой вкладке открываем логин страницу
        pageProvider.getLoginPage().openLoginPage();

        // Шаг 7: Проверяем, что кнопка SignOut видна в новой вкладке
        pageProvider.getHomePage().checkIsButtonSignOutVisible();

        // Шаг 8-9: Возвращаемся на основной таб и проверяем SignOut
        pageProvider.getHomePage()
                .switchToMainTab()
                .checkIsButtonSignOutVisible();
    }

    @Test
    public void testInputsAreClearedAfterRefresh() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(TestData.ValidLogin)
                .enterTextIntoInputPassword(TestData.ValidPassword)
                .refreshPage()
                .clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsButtonSignOutNotVisible();
    }

    @Test
    public void validLoginWithTabAndEnter() {
        pageProvider.getLoginPage().openLoginPage();

        // Actions
        Actions actions = new Actions(webDriver);

        //  TAB → TAB → ввод логина → TAB → ввод пароля → ENTER
        actions
                .sendKeys(Keys.TAB)                    // focus Username
                .sendKeys(Keys.TAB)
                .sendKeys(TestData.ValidLogin)         // ввод логина БЕЗ элемента
                .sendKeys(Keys.TAB)                    // переход к Password
                .sendKeys(TestData.ValidPassword)      // ввод пароля
                .sendKeys(Keys.ENTER)                  // submit
                .perform();

        //  Проверка
        pageProvider.getHomePage().checkIsButtonSignOutVisible();
    }
    @Test
    public void testRegistrationErrorsWithTabAndEnter() {
        LoginPage loginPage = pageProvider.getLoginPage().openLoginPage();

        // Actions
        Actions actions = new Actions(webDriver);

        // Шаги 2-7: перемещение по полям и ввод данных через клавиши
        actions
                .sendKeys(Keys.TAB) // TAB до User Name
                .sendKeys(Keys.TAB) // TAB до User Name в форме регистрации
                .sendKeys("invalidUser!") // невалидный username
                .sendKeys(Keys.TAB) // TAB до Email
                .sendKeys("invalidEmail") // невалидный email
                .sendKeys(Keys.TAB) // TAB до Password
                .sendKeys("123") // невалидный пароль
                .sendKeys(Keys.ENTER) // Submit формы
                .perform();

        // Шаг 8-9: проверяем ошибки
        loginPage.checkInvalidLoginError();

    }

}

