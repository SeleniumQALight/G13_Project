package org.apiTests;

import io.restassured.http.ContentType;
import org.api.EndPointsBooks;
import org.api.dto.requestDto.AddBookRequest;
import org.api.dto.requestDto.LoginRequest;
import org.api.dto.responseDto.BooksResponse;
import org.api.dto.responseDto.LoginResponse;
import org.api.dto.responseDto.UserResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AddBookTest {
    String username = "VPokydko";
    String password = "Forbidden403!";

    @Test
    public void userCanLoginAndGetToken() {
        LoginRequest request = new LoginRequest(username, password);

        LoginResponse login = given()
                .log().all()
                .baseUri(EndPointsBooks.BASE_URL)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);

        String token = login.token;
        String userId = login.userId;

        System.out.println("Token: " + token);
        System.out.println("UserId: " + userId);

        given()
                .baseUri(EndPointsBooks.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .statusCode(204);

        BooksResponse books = given()
                .baseUri(EndPointsBooks.BASE_URL)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .statusCode(200)
                .extract()
                .as(BooksResponse.class);

        String isbn = books.books.get(0).isbn;
        System.out.println("Book ISBN = " + isbn);

        AddBookRequest addBook = new AddBookRequest(
                userId,
                List.of(new AddBookRequest.Isbn(isbn))
        );

        given()
                .baseUri(EndPointsBooks.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(addBook)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .statusCode(201);

        UserResponse user = given()
                .baseUri(EndPointsBooks.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/" + userId)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .as(UserResponse.class);

        Assert.assertEquals(1, user.books.size());
        Assert.assertEquals(isbn, user.books.get(0).isbn);
    }
}