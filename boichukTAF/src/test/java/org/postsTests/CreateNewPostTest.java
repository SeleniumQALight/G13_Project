package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    private final String POST_TITLE = "G13 Boichuk111";
    @Test
    public void createNewPost() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("G13 Boichuk Body")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToCreatePostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitlePresent(POST_TITLE, 1);
    }

    @After
    public void deletePosts(){
        logger.info("Post condition - delete post");
        pageProvider.getHomePage()
                .openHomePageAndLoginNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }
}
