package org.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

public class AddMultipleBookTest {

    private String token;
    private String userId;
    private final String username = "Alyona";
    private final String password = "Qwerty12345!";

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://demoqa.com";

        Response loginResponse = given()
                .contentType(ContentType.JSON)
                .body("{\"userName\":\"" + username + "\", \"password\":\"" + password + "\"}")
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().all()
                .extract()
                .response();

        token = loginResponse.path("token");
        userId = loginResponse.path("userId");

        assertNotNull("Token should not be null", token);
        assertNotNull("UserId should not be null", userId);


        Response deleteResponse = given()
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .log().all()
                .extract()
                .response();

        int deleteStatus = deleteResponse.getStatusCode();
        assert (deleteStatus == HttpStatus.SC_OK || deleteStatus == HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void addBookToUserAndVerify() {
        SoftAssertions softAssertions = new SoftAssertions();

        Response allBooksResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        String isbn = allBooksResponse.jsonPath().getString("books[0].isbn");

        Response addBookResponse = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body("{\"userId\":\"" + userId + "\", \"collectionOfIsbns\":[{\"isbn\":\"" + isbn + "\"}]}")
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().all()
                .extract()
                .response();

        softAssertions.assertThat(addBookResponse.getStatusCode())
                .isIn(HttpStatus.SC_OK, HttpStatus.SC_CREATED);

        Response userBooksResponse = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/" + userId)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        String addedIsbn = userBooksResponse.jsonPath().getString("books[0].isbn");
        softAssertions.assertThat(addedIsbn).isEqualTo(isbn);


        softAssertions.assertAll();
    }
}