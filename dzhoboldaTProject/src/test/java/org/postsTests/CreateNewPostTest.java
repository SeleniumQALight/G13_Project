package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    private final String POST_TITLE = "G13 DZHOBOLDA1236";

    @Test
    public  void createNewPost() {
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


