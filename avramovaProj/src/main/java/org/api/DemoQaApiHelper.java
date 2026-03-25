package org.api;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.api.dto.requestDto.DemoQaAddBookDto;
import org.api.dto.requestDto.DemoQaIsbnDto;
import org.api.dto.responseDto.DemoQaAllBooksDto;
import org.api.dto.responseDto.DemoQaLoginDto;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DemoQaApiHelper {

    public DemoQaLoginDto login(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        return given()
                .spec(ApiHelper.requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(DemoQaEndPoints.DEMOQA_LOGIN)
                .then()
                .spec(ApiHelper.responseSpecification)
                .extract().response().as(DemoQaLoginDto.class);
    }

    private ResponseSpecification getFlexibleResponseSpec() {
        return new ResponseSpecBuilder()
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }

    public DemoQaAllBooksDto getAllBooks() {
        return given()
                .spec(ApiHelper.requestSpecification)
                .when()
                .get(DemoQaEndPoints.DEMOQA_GET_BOOKS)
                .then()
                .spec(ApiHelper.responseSpecification)
                .extract().response().as(DemoQaAllBooksDto.class);
    }

    public void deleteAllBooksByUser(String token, String userId) {
        given()
                .spec(ApiHelper.requestSpecification)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(DemoQaEndPoints.DEMOQA_DELETE_BOOKS)
                .then()
                .spec(getFlexibleResponseSpec())
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    public void addBookToUser(String token, String userId, String isbn) {
        DemoQaIsbnDto isbnDto = DemoQaIsbnDto.builder().isbn(isbn).build();
        List<DemoQaIsbnDto> isbnList = Collections.singletonList(isbnDto);

        DemoQaAddBookDto addBookBody = DemoQaAddBookDto.builder()
                .userId(userId)
                .collectionOfIsbns(isbnList)
                .build();

        given()
                .spec(ApiHelper.requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body(addBookBody)
                .when()
                .post(DemoQaEndPoints.DEMOQA_POST_BOOKS)
                .then()
                .spec(getFlexibleResponseSpec())
                .statusCode(HttpStatus.SC_CREATED);
    }

    public DemoQaLoginDto getUserInfo(String token, String userId) {
        return given()
                .spec(ApiHelper.requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(DemoQaEndPoints.DEMOQA_USER, userId)
                .then()
                .spec(ApiHelper.responseSpecification)
                .extract().response().as(DemoQaLoginDto.class);
    }
}
