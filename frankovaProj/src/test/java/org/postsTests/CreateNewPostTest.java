package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreateNewPost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle("G13 Frankova")
                .enterTextIntoInputBody("G13 Frankova Some Body")
                .selectTextInDropdownAccess("Приватне повідомлення")
                .enterStateForCheckbox("check")

                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkStateOfCheckboxInCreatedPost("check")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

    }
}
