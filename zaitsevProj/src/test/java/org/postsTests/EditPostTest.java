package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {
    private final String POST_TITLE = "G13 Zaitsev Title for Edit Post";
    private final String POST_BODY = "G13 Zaitsev Body for Edit Post";
    @Before
    public void preconditionCreatePost() {
        logger.info("Precondition - create post");
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .selectTextInDropdownAccess("Приватне повідомлення")
                .setUniquePostCheckBox("uncheck")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkUniquePostCheckBoxState("no")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitlePresent("G13 Zaitsev Title for Edit Post", 1);
    }

    @Test
    public void editPost() {
        String editedTitle = POST_TITLE + " Edited";
        pageProvider.getMyProfilePage()
                .editPostWithTitle(POST_TITLE)
                .getEditPostPage()
                .checkIsRedirectToEditPostPage()
                .enterTextIntoInputTitle(editedTitle)
                .clickOnSaveUpdatesButton()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitlePresent(editedTitle,1);
    }

    @After
    public void deletePosts(){
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE + " Edited")
                .deletePostsTillPresent(POST_TITLE);

    }

}
