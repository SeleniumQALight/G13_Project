package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {

    //GUID =1d1d1d1d1d1d1d1d1d1d1d1d1d1d1d1d
    private final String POST_TITLE = "TC01 G13 SOLART937111"+ Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void TC01_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFromWithValidCrea()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreateNewPost()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("G13 SOLAR Some body text")
                .selectTextInDropDownAccess("Приватне повідомлення")

                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMassagelsDisplay()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitlePresent(POST_TITLE, 1);
    }

    @After
    public void deletePost() {
        logger.info("Post completion started");
        pageProvider.getHopePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }
}
