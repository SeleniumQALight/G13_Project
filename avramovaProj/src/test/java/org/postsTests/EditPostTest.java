package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class EditPostTest extends BaseTest {

    private String uniqueTitle;
    private String updatedTitle;

    @Test
    public void editPostTest(){
        uniqueTitle = "G13 Avramova EditPost " + System.currentTimeMillis();
        updatedTitle = uniqueTitle + " - Updated";

        // Create post
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .enterTextIntoInputTitle(uniqueTitle)
                .enterTextIntoInputBody("Some body")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        // Edit the post
        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .clickOnPostWithTitle(uniqueTitle) // Клікаємо на пост у списку
                .clickOnEditButton()               // Клікаємо на "олівець" на сторінці поста
                .enterTextIntoInputTitle(updatedTitle)
                .clickOnSaveChangesButton();       // Зберігаємо

        // Verify success message
        pageProvider.getPostPage()
                .checkTextInSuccessMessage("Post successfully updated.");

        // Verify in My Profile
        pageProvider.getPostPage().getHeaderForLoggedUserElement().clickOnButtonMyProfile();
        pageProvider.getMyProfilePage()
                .checkPostWithTitleIsPresent(updatedTitle, 1);
    }

    @After
    public void deletePost(){
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(uniqueTitle)
                .deletePostsTillPresent(updatedTitle);
    }
}



/*написати тест на зміну поста (створивши новий класс) у якому :
- залогінитися
- створити пост з унікальним title в прекондішені теста
- змінити тайтл поста
- перевірити що ви бачите змінений пост в списку постів в myprofile.
- в посткондішені видалити змінений пост, і не змінений (якщо він раптом не змінився)
 */