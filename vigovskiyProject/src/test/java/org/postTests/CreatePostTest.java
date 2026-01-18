package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    private final String POST_TITLE = "G13 Jenya111";
    @Test
    public void createNewPost() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillInLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("G13 Jenya")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed("G13 Jenya")
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitlePresent(POST_TITLE, 1);


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
    @After
    public void deletePost() {
        logger.info("Post condition -delete post");
        pageProvider.getHomePage().openHomePageAndLogInNeeded()
                .getheaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(POST_TITLE);

    }
}
