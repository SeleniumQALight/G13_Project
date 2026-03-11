package org.postTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.Utils_Custom;

public class EditPostTest extends BaseTest {

    private final String POST_TITLE = "Stf-Post-Title" + Utils_Custom.getDateAndTimeFormatted();
    private final String EDITED_POST_TITLE = "Stf-Edited-Post-Title" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    public void editPostTest(){

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred();

        pageProvider.getHomePage()
                .clickOnButtonCreatePost()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("stf post body")
                .clickOnSaveNewPostButton();

        pageProvider.getPostPage()
                .clickOnEditButton()
                .enterTextIntoInputTitle(EDITED_POST_TITLE)
                .clickOnSaveUpdatesButton();

        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitlePresent(EDITED_POST_TITLE,1);
    }

    @After
    public void deletePosts(){

        logger.info("Post condition - delete posts");

        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .deletePostsTillPresent(EDITED_POST_TITLE)
                .deletePostsTillPresent(POST_TITLE);
    }

}
