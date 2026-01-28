package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LogOutTest extends BaseTest {

    @Test
    public void logOutTest() {
        //Після логіну перевірити наявність елементів в Хедері - кнопки пошуку, кнопки виклику чатику, аватарки, кнопки CreatePost , кнопки signOut

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement()
                .checksButtonSearchVisible()
                .checksButtonChatVisible()
                .checksAvatarVisible()
                .checksButtonCreatePostVisible()
                .checksButtonSignOutVisible()

                .clickOnButtonSignOut();

//перевірити відсутність елементів в Хедері - кнопки пошуку, кнопки виклику чатику, аватарки, кнопки CreatePost , кнопки signOut, а також наявність поля для вводу логіна, пароля, та кнопки sign In.
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checksButtonSearchNotVisible()
                .checksButtonChatNotVisible()
                .checksAvatarNotVisible()
                .checksButtonCreatePostNotVisible()
                .checksButtonSignOutNotVisible();
    }
}
