package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.api.dto.requestDto.AddBookRequestDto;
import org.api.dto.requestDto.LoginRequestDto;
import org.api.dto.responseDto.BooksResponseDto;
import org.api.dto.responseDto.LoginResponseDto;
import org.api.dto.responseDto.UserBooksResponseDto;


import static io.restassured.RestAssured.given;

public class ApiHelperFoDemoQa {
    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(EndPoints.BASE_URL_DEMOQA)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(200)
            .build();

    public LoginResponseDto login(String userName, String password) {

        LoginRequestDto request = LoginRequestDto.builder()
                .userName(userName)
                .password(password)
                .build();

        return given()
                .spec(requestSpecification)
                .body(request)
                .when()
                .post(EndPoints.LOGIN)
                .then()
                .statusCode(200)
                .extract()
                .as(LoginResponseDto.class);
    }

    public BooksResponseDto getAllBooks() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.GET_ALL_BOOKS)
                .then()
                .spec(responseSpecification)
                .extract().as(BooksResponseDto.class);
    }

    public UserBooksResponseDto getUserBooks(String token, String userId) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.GET_USER.replace("{userId}", userId))
                .then()
                .statusCode(200)
                .extract().as(UserBooksResponseDto.class);
    }
    public void addBook(String token, AddBookRequestDto request) {
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(EndPoints.ADD_BOOK)
                .then()
                .statusCode(201);
    }

    public void deleteAllUserBooks(String token, String userId) {
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(EndPoints.DELETE_BOOKS)
                .then()
                .statusCode(204);
    }
}
