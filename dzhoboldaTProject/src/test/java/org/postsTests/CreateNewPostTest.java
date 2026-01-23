package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class CreateNewPostTest extends BaseTest {
    //GUID =  G13 DZHOBOLDA1237gggfgfdf
    private final String POST_TITLE = "TC01 G13 DZHOBOLDA" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public  void TC01_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithVailidCred()
                .checkRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("G13 DZH  body text")
                .selectTextInDropDownAccess("Приватне повідомлення")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessagesDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile();


        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitelProfile(POST_TITLE, 1);
    }

        @After
        public  void deletePost(){
        logger.info("Post condition - delete post");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePosTitlePresent(POST_TITLE);

        }
    }


