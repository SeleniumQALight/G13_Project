package org.apiTests;

import io.restassured.http.ContentType;
import org.api.ApiHelperPrivatbank;
import org.api.EndPointsBooks;
import org.api.dto.requestDto.AddBookRequest;
import org.api.dto.requestDto.LoginRequest;
import org.api.dto.responseDto.BooksResponse;
import org.api.dto.responseDto.LoginResponse;
import org.api.dto.responseDto.UserResponse;
import org.junit.Test;


public class AddBookTest {
    String username = "VPokydko";
    String password = "Forbidden403!";
    ApiHelperPrivatbank api = new ApiHelperPrivatbank();

    @Test
    public void addBookTest() {

        LoginResponse login = api.login(username, password);

        String token = login.token;
        String userId = login.userId;

        api.deleteAllBooks(userId, token);

        String isbn = api.getFirstBookIsbn();

        api.addBook(userId, token, isbn);

        UserResponse user = api.getUser(userId, token);

        api.assertBookAdded(user, isbn);
    }
}