package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.pages.MyProfilePage;
import org.pages.PostPage;

public class EditPostTest extends BaseTest {

    private final String OLD_TITLE =
            "OLD POST " + System.currentTimeMillis();

    private final String NEW_TITLE =
            "UPDATED POST " + System.currentTimeMillis();

    @Test
    public void TC_editPostTitle() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithVailidCred()
                .clickOnButtonCreatePost()
                .enterTextIntoInputTitle(OLD_TITLE)
                .enterTextIntoInputBody("body text")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.");

        // 2. ДІЯ: Редагуємо цей пост
        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile(); // Переходимо в профіль

        pageProvider.getMyProfilePage()
                .clickOnPostWithTitle(OLD_TITLE) // Находим пост, попадаем на PostPage
                .clickOnEditButton()             // Кликаем Edit, попадаем на EditPostPage
                .checkIsRedirectToEditPostPage() // Проверяем, что мы на нужной странице
                .enterNewTitle(NEW_TITLE)        // Вводим текст
                .clickOnSaveUpdatesButton()      // Жмем сейв, возвращаемся на PostPage
                .checkPostWasCreatedMessagesDisplayed(); // Проверяем алерт успеха

        // 3. ПЕРЕВІРКА: чи бачимо новий тайтл у профілі (як у твоєму робочому тесті)
        pageProvider.getPostPage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitelProfile(NEW_TITLE, 1); // Перевіряємо, що пост з НОВИМ ім'ям є
    }

    @After
    public void deletePosts() {
        logger.info("Post condition - delete posts");
        // Видаляємо обидва на всяк випадок (якщо тест впав на середині)
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .deletePosTitlePresent(NEW_TITLE)
                .deletePosTitlePresent(OLD_TITLE);
    }
    }


