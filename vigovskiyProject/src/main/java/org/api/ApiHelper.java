package org.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.dto.requestDto.CreateNewPostDto;
import org.api.dto.responseDto.PostsDto;
import org.data.TestData;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import org.api.dto.responseDto.CurrencyRate;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .addFilter(new AllureRestAssured())
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();


    public ValidatableResponse getAllPostsByUserRequest(String userName, int expectedStausCode) {
        return given()
//                .contentType(ContentType.JSON)
//                .log().all()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POSTS_BY_USER, userName)
                .then()
                .log().all()
                .statusCode(expectedStausCode);
    }

    public String getToken() {
        return getToken(TestData.VALID_USERNAME_API, TestData.VALID_PASSWORD_API);
    }

    public String getToken(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPoints.LOGIN)
                .then()
//                .statusCode(HttpStatus.SC_OK)
//                .log().all();
                .spec(responseSpecification)
                .extract().body().asString().replace("\"", "");
    }

    public PostsDto[] getAllPostsByUserInObject() {
        return getAllPostsByUserInObject(TestData.VALID_USERNAME_API, HttpStatus.SC_OK);

    }

    public PostsDto[] getAllPostsByUserInObject(String userName, int status) {
        return getAllPostsByUserRequest(userName, status)
                .extract().body()
                .as(PostsDto[].class);
    }

    public void deleteAllPostsTillPresent(String validUsernameApi, String token) {
        PostsDto[] listOfPosts = this.getAllPostsByUserInObject(validUsernameApi, HttpStatus.SC_OK);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info(
                    String.format("Post with id %s and title %s was deleted", listOfPosts[i].getId(), listOfPosts[i].getTitle()));
        }
    }

    private void deletePostById(String token, String id) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", token);

        given()
                .spec(requestSpecification)
                .body(bodyRequest)
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .spec(responseSpecification);
    }

    public void createPosts(Integer numberOfPosts, String actualToken, Map<String, String> postsData) {
        for (int i = 0; i < numberOfPosts; i++) {
            CreateNewPostDto bodyForPostCreation =
                    CreateNewPostDto.builder()
                            .title(postsData.get("title") + " " + i)
                            .body(postsData.get("body"))
                            .select1(postsData.getOrDefault("select", "All Users"))
                            .uniquePost(postsData.get("uniquePost") == null ? "no" : postsData.get("uniquePost"))
                            .token(actualToken)
                            .build();

            given()
                    .spec(requestSpecification)
                    .body(bodyForPostCreation)
                    .when()
                    .post(EndPoints.CREATE_POST)
                    .then()
                    .spec(responseSpecification);

        }
    }

    public List<CurrencyRate> getPrivatBankRates() {

        CurrencyRate[] response =
                given()
                        .spec(requestSpecification)
                        .when()
                        .get(PrivatBankEndPoints.PRIVAT_API_EXCHANGE_COURSE)
                        .then()
                        .spec(responseSpecification)
                        .extract()
                        .as(CurrencyRate[].class);

        return Arrays.asList(response);
    }
}
