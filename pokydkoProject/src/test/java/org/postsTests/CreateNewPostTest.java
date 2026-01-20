package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle("G13 Vladislav's post")
                .enterTextIntoInputBody("G13 Vladislav's Some body text")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .setCheckboxUniquePostState("check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUnique("yes")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();
    }
}
