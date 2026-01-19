package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.data.TestData.*;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost(){
        String postTitle = "G13 @i.martyn" + LocalDateTime.now();

        pageProvider.getLoginPage()
                .getLoginPageAndFillLoginFormWithValidCreds(VALID_LOGIN, VALID_PASSWORD)
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .checkIsInputTitleEnabled(true)
                .enterTextIntoInputTitle(postTitle)
                .checkIsInputBodyEnabled(true)
                .enterTextIntoInputBody("G13 @i.martyn: post from autotest")
                .checkCheckboxUniquePost()
                .selectTextInDropdownAccesss("Приватне повідомлення")
                .checkIsButtonSaveNewPostEnabled(true)
                .clickOnButtonSaveNewPost();

        pageProvider.getPostPage().checkIsRedirectToPostPage()
                .checkIsPostCreatedSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUnique("yes")
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsProfilePageOpened()
                .checkIsCreatedPostListed(postTitle);

    }

    @Test
    public void postNotCreatedWithEmptyFields() {
        pageProvider.getLoginPage().openLoginPage()
                .getLoginPageAndFillLoginFormWithValidCreds(VALID_LOGIN, VALID_PASSWORD)
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .clickOnButtonSaveNewPost();
        pageProvider.getPostPage().checkPostPageIsNotOpened();
        pageProvider.getCreatePostPage().checkIsInputTitleEnabled(true)
                .checkIsInputBodyEnabled(true)
                .checkIsButtonSaveNewPostEnabled(true);
    }
}
