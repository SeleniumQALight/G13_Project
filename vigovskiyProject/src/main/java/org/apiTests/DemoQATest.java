package org.apiTests;

import org.api.ApiHelperFoDemoQa;
import org.api.dto.requestDto.AddBookRequestDto;
import org.api.dto.responseDto.BookDto;
import org.api.dto.responseDto.BooksResponseDto;
import org.api.dto.responseDto.LoginResponseDto;
import org.api.dto.responseDto.UserBooksResponseDto;
import org.data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DemoQATest {

    ApiHelperFoDemoQa apiHelper = new ApiHelperFoDemoQa();

    String token;
    String userId;

    @Before
    public void loginAndCleanBooks() {

        //Логинимся
        LoginResponseDto loginResponse =
                apiHelper.login(
                        TestData.VALID_LOGIN_FOR_DEMOQA,
                        TestData.VALID_PASSWORD_FOR_DEMOQA
                );

        token = loginResponse.getToken();
        userId = loginResponse.getUserId();

        //Удаляем все книги пользователя
        apiHelper.deleteAllUserBooks(token, userId);
    }

    @Test
    public void addBookToUserTest() {

        //Получаем список всех книг
        BooksResponseDto allBooks = apiHelper.getAllBooks();

        String isbnToAdd = allBooks
                .getBooks()
                .get(0)
                .getIsbn();

        //Добавляем книгу пользователю
        AddBookRequestDto request = AddBookRequestDto.builder()
                .userId(userId)
                .collectionOfIsbns(List.of(new BookDto(isbnToAdd)))
                .build();

        apiHelper.addBook(token, request);

        //Получаем книги пользователя
        UserBooksResponseDto userBooks =
                apiHelper.getUserBooks(token, userId);

        //Проверяем
        Assert.assertEquals(1, userBooks.getBooks().size());

        Assert.assertEquals(
                isbnToAdd,
                userBooks.getBooks().get(0).getIsbn()
        );
    }
}