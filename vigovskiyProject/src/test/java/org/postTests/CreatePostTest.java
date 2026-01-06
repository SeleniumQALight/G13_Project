package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost() {

        pageProvider.getLoginPage().openLoginPageAndFillInLoginFormWithValidCred();
//        String title = "My first post";
//        String body = "This is the body of my first post";
//
//        pageProvider.getLoginPage()
//                .openLoginPage()
//                .enterTestIntoInputLogin("qaauto")
//                .enterTextIntoInputPassword("123456qwerty")
//                .clickOnButtonSignIn();
//
//        pageProvider.getHomePage()
//                .clickOnCreatePostButton();
//
//        pageProvider.getCreatePostPage()
//                .enterTextIntoPostTitle(title)
//                .enterTextIntoPostBody(body)
//                .clickOnSavePostButton();
//
//        pageProvider.getPostPage()
//                .checkPostWasCreatedWithTitle(title)
//                .checkPostWasCreatedWithBody(body);
    }
}
