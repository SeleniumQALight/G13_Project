package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.*;

public class CreateNewPostTest extends BaseTest {
    private final String POST_TITLE = "G13 Ostapenko1";

    @Test
    public void createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("G13 Ostapenko Some body")
                .setStateToCheckbox("check")
                .selectTextInDropDownAccess("Приватне повідомлення")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkPostIsUniqueByState("yes")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitle(POST_TITLE, 1);
    }

    @After
    public void deletePost() {
        logger.info("Start delete post");
        pageProvider.getHomePage()
                .openHomePageAndLoginNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleWhilePresent(POST_TITLE);
    }
}
