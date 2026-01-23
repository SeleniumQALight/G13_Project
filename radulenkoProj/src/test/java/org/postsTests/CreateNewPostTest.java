package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
   private final String POST_TITLE = "G13 Taras111";
    @Test
    public void createNewPost(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("G13 Taras Some body")
                .selectTextInDropdownAccess("Приватне повідомлення")

                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIPresent(POST_TITLE, 1);
    }

    @After
    public void deletePosts() {
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }
}
