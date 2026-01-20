package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPostTest extends BaseTest {

    private final String POST_TITLE = "G13 Frankova11111";
    private final String UPDATED_POST_TITLE = "G13 FrankovaUpdated";

    @Before
    public void logInAndCreatePost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreateNewPost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("G13 Frankova Some Body")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .enterStateForCheckboxUniquePost("check")

                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkStateOfCheckboxInCreatedPost("check")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();


    }


    @Test
    public void editPostTest() {
        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkIsPostWithTitlePresent(POST_TITLE, 1)
                .clickOnPostWithTitle(POST_TITLE)
                .checkIsRedirectToPostPage()
                .clickOnEditPostButton()
                .checkIsRedirectToEditPostPage()
                .enterTextIntoInputTitleEdit(UPDATED_POST_TITLE)
                .clickOnButtonSaveUpdates()
                .clickOnBackToPostPermalink()
                .checkIsRedirectToPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkIsPostWithTitlePresent(UPDATED_POST_TITLE, 1);
    }

    @After
    public void deletePost(){
        logger.info ("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(UPDATED_POST_TITLE)
                .deletePostsTillPresent(POST_TITLE);
    }


}

