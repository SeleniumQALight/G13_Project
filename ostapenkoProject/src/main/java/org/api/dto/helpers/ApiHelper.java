package org.api.dto.helpers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.EndPoints;
import org.api.dto.requestDto.CreateNewPostDTO;
import org.api.dto.responseDto.PostsDto;
import org.data.TestData;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

    public ValidatableResponse getAllPostsByUserRequest(String username, int expectedStatusCode) {
        return given()
//                .contentType(ContentType.JSON)
//                .log().all()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POSTS_BY_USER, username)
                .then()
                .log().all()
                .statusCode(expectedStatusCode);
    }

    public String getToken() {
        return getToken(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }

    public String getToken(String username, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPoints.LOGIN)
                .then()
//                .statusCode(HttpStatus.SC_OK)
//                .log().all()
                .spec(responseSpecification)
                .extract().body().asString().replaceAll("\"", "");

    }

    public PostsDto[] getAllPostsByUserInObject() {
        return getAllPostsByUserInObject(TestData.VALID_LOGIN_API, HttpStatus.SC_OK);
    }

    public PostsDto[] getAllPostsByUserInObject(String username, int expectedStatusCode) {
        return getAllPostsByUserRequest(username, expectedStatusCode)
                .extract().body().as(PostsDto[].class);
    }

    public void deleteAllPostsTillPresent(String validLoginApi, String actualToken) {
        PostsDto[] posts = this.getAllPostsByUserInObject(validLoginApi, HttpStatus.SC_OK);

        for (int i = 0; i < posts.length; i++) {
            deletePostById(actualToken, posts[i].getId());
            logger.info("Post with id %s and title %s was deleted"
                    .formatted(posts[i].getId(), posts[i].getTitle()));
        }
    }

    private void deletePostById(String actualToken, String id) {
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("token", actualToken);

        given()
                .spec(requestSpecification)
                .body(requestBody)
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .spec(responseSpecification);
    }

    public void createPosts(Integer numberOfPosts, String actualToken, Map<String, String> postsData) {
        for (int i = 0; i < numberOfPosts; i++) {
            CreateNewPostDTO bodyForPostCreation = CreateNewPostDTO.builder()
                    .title(postsData.get("title") + " " + i)
                    .body(postsData.get("body") + " " + i)
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
}