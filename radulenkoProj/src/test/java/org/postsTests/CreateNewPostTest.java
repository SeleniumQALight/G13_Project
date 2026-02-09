package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    // GUID = 1b1b1b1b-1b1b-1b1b-1b1b-1b1b1b1b1b1b
   private final String POST_TITLE = "TC01 G13 Taras" + Utils_Custom.getDateAndTimeFormatted();
    @Test
    public void TC01_createNewPost(){
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
