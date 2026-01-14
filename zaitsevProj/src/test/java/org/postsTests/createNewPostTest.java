package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class createNewPostTest extends BaseTest {
    @Test
    public void createNewPost() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle("G13 Zaitsev Title")
                .enterTextIntoInputBody("G13 Zaitsev Body")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .setUniquePostCheckBox("uncheck")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToCreatePostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkUniquePostCheckBoxState("no")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();
    }
}
