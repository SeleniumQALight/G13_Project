package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.*;

public class EditPostTest extends BaseTest {

    private final String ORIGINAL_POST_TITLE = "Post title " + System.currentTimeMillis();

    private final String UPDATED_POST_TITLE = "Updated post title " + System.currentTimeMillis();
    private final String POST_BODY = "G13 Ostapenko Some body";

    @Before
    public void login() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(ORIGINAL_POST_TITLE)
                .enterTextIntoInputBody(POST_BODY)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();
    }

    @Test
    public void editPost() {

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .openPostDetails(ORIGINAL_POST_TITLE, 1)
                .clickOnEditButton()
                .checkIsRedirectToEditPostPage()
                .enterTextIntoInputTitle(UPDATED_POST_TITLE)
                .clickOnSaveUpdatesButton()

                .checkPostWasEditedMessageIsDisplayed();

        logger.info("Title is updated");

        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkPostWithTitle(UPDATED_POST_TITLE, 1);

        logger.info("Post with updated title is found");

    }

    @After
    public void deletePost() {
        logger.info("Start delete post(s)");

        pageProvider.getHomePage()
                .openHomePageAndLoginNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()

                // delete edited post (if it exists)
                .deletePostWithTitleWhilePresent(UPDATED_POST_TITLE)

                // delete original post (if it was not edited for some reason)
                .deletePostWithTitleWhilePresent(ORIGINAL_POST_TITLE);

        logger.info("Postcondition finished: posts deleted if present");
    }
}