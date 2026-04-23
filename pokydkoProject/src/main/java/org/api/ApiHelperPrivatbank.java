package org.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.api.dto.requestDto.AddBookRequest;
import org.api.dto.requestDto.LoginRequest;
import org.api.dto.responseDto.BooksResponse;
import org.api.dto.responseDto.LoginResponse;
import org.api.dto.responseDto.UserResponse;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatbank {

    public ValidatableResponse getExchangeRatesByDate(String date, int expectedStatusCode) {
        return given()
                .baseUri(EndPointsPrivatbank.BASE_URL)
                .contentType(ContentType.JSON)
                .queryParam("date", date)
                .log().all()
                .when()
                .get(EndPointsPrivatbank.EXCHANGE_RATES)
                .then()
                .statusCode(expectedStatusCode)
                .log().all();
    }

    public LoginResponse login(String username, String password) {

        LoginRequest request = new LoginRequest(username, password);

        return given()
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
    }

    public void deleteAllBooks(String userId, String token) {

        given()
                .baseUri(EndPointsBooks.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .statusCode(204);
    }

    public String getFirstBookIsbn() {

        BooksResponse books = given()
                .baseUri(EndPointsBooks.BASE_URL)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .statusCode(200)
                .extract()
                .as(BooksResponse.class);

        return books.books.get(0).isbn;
    }

    public void addBook(String userId, String token, String isbn) {

        AddBookRequest request = new AddBookRequest(
                userId,
                List.of(new AddBookRequest.Isbn(isbn))
        );

        given()
                .baseUri(EndPointsBooks.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .statusCode(201);
    }

    public UserResponse getUser(String userId, String token) {

        return given()
                .baseUri(EndPointsBooks.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/" + userId)
                .then()
                .statusCode(200)
                .extract()
                .as(UserResponse.class);
    }

    public void assertBookAdded(UserResponse user, String expectedIsbn) {

        Assert.assertEquals(1, user.books.size());
        Assert.assertEquals(expectedIsbn, user.books.get(0).isbn);
    }

}
