package org.postsTest;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    //GUID = 1b1c3e2e-87f4-11ee-be56-0242ac120002
    private final String POST_TITLE = "TC01 G13 Nelly "+ Utils_Custom.getDateAndTimeFormatted();
    @Test
    public void TC01_createNewPostTest() {
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
