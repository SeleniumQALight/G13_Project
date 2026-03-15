package org.apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.api.dto.requesteDto.AddBookRequestDto;
import org.api.dto.requesteDto.LoginRequestDto;
import org.dto.IsbnDto;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AddBookApiTest {

    String baseUrl = "https://demoqa.com";

    @Test
    public void addBookToUserTest() {

        RestAssured.baseURI = baseUrl;

        // LOGIN BODY
        LoginRequestDto loginBody = LoginRequestDto.builder()
                .userName("aostapenko")
                .password("HelloJopp@90_")
                .build();

        // LOGIN REQUEST
        Response loginResponse =
                given()
                        .log().all()
                        .contentType("application/json")
                        .body(loginBody)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        String token = loginResponse.jsonPath().getString("token");
        String userId = loginResponse.jsonPath().getString("userId");

        // DELETE ALL USER BOOKS
        given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/BookStore/v1/Books?UserId=" + userId)
                .then()
                .log().all()
                .statusCode(anyOf(is(204), is(200)));

        // GET ALL BOOKS
        Response booksResponse =
                given()
                        .log().all()
                        .when()
                        .get("/BookStore/v1/Books")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        String isbn = booksResponse.jsonPath().getString("books[0].isbn");

        // ADD BOOK BODY
        AddBookRequestDto addBookBody = AddBookRequestDto.builder()
                .userId(userId)
                .collectionOfIsbns(
                        List.of(
                                IsbnDto.builder()
                                        .isbn(isbn)
                                        .build()
                        )
                )
                .build();

        // ADD BOOK REQUEST
        given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(addBookBody)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().all()
                .statusCode(201);

        // VERIFY BOOK ADDED
        given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/" + userId)
                .then()
                .log().all()
                .statusCode(200)
                .body("books.size()", equalTo(1))
                .body("books[0].isbn", equalTo(isbn));
    }
}