package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    private final String POST_TITLE_CHECKED = "G13 Yulii";
    private final String POST_TITLE_UNCHECKED = "G13 Yulii unchecked";


    // GUID = 9f3b3f4e-5d6a-4c2e-8f7a-123456789abc
    private final String POST_TITLE = "TC01 G13 Yulii" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TC01_createNewPost() {
        //test steps should be here
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE_CHECKED)
                .enterTextIntoInputBody("G13 Yulii Some body")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .setCheckBoxState("check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkIsPostUnique("yes")
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        // create the same post with unchecked checkbox via home page
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(POST_TITLE_UNCHECKED)
                .enterTextIntoInputBody("G13 Yulii Some body")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .setCheckBoxState("uncheck")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkIsPostUnique("no")
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitlePresent(POST_TITLE_CHECKED, 1)
                .checkPostWithTitlePresent(POST_TITLE_UNCHECKED, 1);
    }
    @After
    public void deletePosts(){
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE_CHECKED)
                .deletePostsTillPresent(POST_TITLE_UNCHECKED);



    }
}
