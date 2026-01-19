package org.postsTest;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    private final String POST_TITLE = "G13 Nelly Title_001";
    @Test
    public void createNewPostTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("G13 Nelly Body")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .clickOnSaveNewPostButton()
                .chechIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHederForLoggedUserElement().clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitlelPresent(POST_TITLE,1);

    }

    @After
    public void deletePosts(){
        logger.info("Deleting posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHederForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }
}
