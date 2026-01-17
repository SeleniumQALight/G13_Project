package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    @Test
    public  void createNewPost(){
        pageProvider.getLoginPage()
                        .openLoginPageAndFillLoginFormWithVailidCred()
                .checkRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle("G13 DZHOBOLDA")
                .enterTextIntoInputBody("G13 DZH  body text")
                .setCheckboxUniquePostState("check") //new step from hom work 3 lesson
                .selectTextInDropDownAccess("Приватне повідомлення")
                .clickOnSaveNewPostButton()
        .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessagesDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUnique("yes")// check of status
        .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile();

    }

}
