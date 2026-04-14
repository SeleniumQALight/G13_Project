package org.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiDemoQaHelper {

    public ApiDemoQaHelper() {
        RestAssured.baseURI = "https://demoqa.com";
    }

    public Response login(String username, String password) {
        return given()
                .contentType(ContentType.JSON)
                .body("{\"userName\":\"" + username + "\", \"password\":\"" + password + "\"}")
                .when()
                .post("/Account/v1/Login")
                .then()
                .extract()
                .response();
    }

    public Response deleteAllBooks(String token, String userId) {
        return given()
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .extract()
                .response();
    }

    public Response getAllBooks() {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .extract()
                .response();
    }

    public Response addBook(String token, String userId, String isbn) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body("{\"userId\":\"" + userId + "\", \"collectionOfIsbns\":[{\"isbn\":\"" + isbn + "\"}]}")
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .extract()
                .response();
    }

    public Response getUserBooks(String token, String userId) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/" + userId)
                .then()
                .extract()
                .response();
    }
}

