package org.apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.api.dto.helpers.BookApiHelper;
import org.api.dto.requesteDto.AddBookRequestDto;
import org.api.dto.requesteDto.LoginRequestDto;
import org.dto.IsbnDto;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AddBookApiTest {

    @Test
    public void addBookToUserTest() {

        RestAssured.baseURI = "https://demoqa.com";
        BookApiHelper api = new BookApiHelper();

        // LOGIN
        LoginRequestDto loginBody = LoginRequestDto.builder()
                .userName("aostapenko")
                .password("HelloJopp@90_")
                .build();

        Response loginResponse = api.login(loginBody);
        loginResponse.then().statusCode(200);

        String token = loginResponse.jsonPath().getString("token");
        String userId = loginResponse.jsonPath().getString("userId");

        // DELETE BOOKS
        api.deleteAllBooks(token, userId)
                .then()
                .log().all()
                .statusCode(anyOf(is(204), is(200)));

        // GET ISBN
        String isbn = api.getAllBooks()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("books[0].isbn");

        // ADD BOOK
        AddBookRequestDto addBookBody = AddBookRequestDto.builder()
                .userId(userId)
                .collectionOfIsbns(List.of(
                        IsbnDto.builder()
                                .isbn(isbn)
                                .build()
                ))
                .build();

        api.addBook(token, addBookBody)
                .then()
                .statusCode(201);

        // VERIFY
        api.getUser(token, userId)
                .then()
                .statusCode(200)
                .body("books.size()", equalTo(1))
                .body("books[0].isbn", equalTo(isbn));
    }
}