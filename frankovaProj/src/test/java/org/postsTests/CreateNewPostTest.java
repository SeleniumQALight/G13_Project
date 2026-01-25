package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {

    //GUID - генерує унік значення для кожного запуску тесту, щоб не було конфлікту з існуючими постами

    private final String POST_TITLE = "TC01 G13 Frankova" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TC01_createNewPost(){
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

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkIsPostWithTitlePresent(POST_TITLE, 1);

    }

    @After
    public void deletePost(){
        logger.info ("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }
}
