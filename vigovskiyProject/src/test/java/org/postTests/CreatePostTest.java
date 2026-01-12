package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    @Test
    public void createNewPost() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillInLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle("G13 Jenya")
                .enterTextIntoInputBody("G13 Jenya")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed("G13 Jenya")
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

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
