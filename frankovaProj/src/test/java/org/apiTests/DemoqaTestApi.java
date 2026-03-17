package org.apiTests;

import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.DemoqaApiHelper;
import org.api.dto.responseDto.DemoqaBookDto;
import org.api.dto.responseDto.DemoqaLoginDto;
import org.data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DemoqaTestApi extends DemoqaBaseTestApi {
    Logger logger = Logger.getLogger(getClass());


    private String token;
    private String userId;


    @Before
    public void getTokenAndUserId() {

        DemoqaLoginDto loginResponse = new DemoqaApiHelper()
                .getTokenAndUserID(TestData.USERNAME_DEMOQA, TestData.PASSWORD_DEMOQA);

        token = loginResponse.getToken();
        userId = loginResponse.getUserId();
    }

    @Test


    public void addNewBookForUser() {

        //1. Перевіряємо початкову кількість книжок у юзера

        List<DemoqaBookDto> userBooksBeforeAdding = new DemoqaApiHelper().getUserBooks(token, userId, HttpStatus.SC_OK);

        //2. Видаляємо книжки юзеру, якщо вони є

        if (!userBooksBeforeAdding.isEmpty()) {
            String resultDeleteAllUserBooks = new DemoqaApiHelper()
                    .deleteAllUserBooks(token, userId, HttpStatus.SC_NO_CONTENT)
                    .extract().response().body().asString();

            logger.info("Books were deleted.");
        } else {
            logger.info("User had no books, skipping deletion.");
        }

        //3. Отримуємо усі доступні книжки
        List<DemoqaBookDto> allBooks = new DemoqaApiHelper().getAllBooks();

        logger.info("All books list: " + allBooks);

        //4. Отримуємо ІД першої у списку книжки
        String firstIsbn = allBooks.get(0).getIsbn();

        logger.info("First ISBN: " + firstIsbn);

        //5. додаємо цю книжку юзеру
        String resultAddBook =
                new DemoqaApiHelper().addBookForUser(token, userId, firstIsbn, HttpStatus.SC_CREATED)
                        .extract().response().body().asString();

        logger.info("Response after the book was added" + resultAddBook);

        //6. Отримуємо список книжок юзера
        List<DemoqaBookDto> userBooksAfterAdding = new DemoqaApiHelper().getUserBooks(token, userId, HttpStatus.SC_OK);

        //7.Перевірки

        // Перевіряємо, що в списку 1 книга
        Assert.assertEquals("Кількість книг у користувача має бути 1", 1, userBooksAfterAdding.size());

        // Перевіряємо, що ISBN доданої книги збігається з очікуваним
        String actualIsbn = userBooksAfterAdding.get(0).getIsbn();
        Assert.assertEquals("ISBN доданої книги не збігається", firstIsbn, actualIsbn);

        logger.info("User has 1 book with Isbn = " + actualIsbn);

    }


}
