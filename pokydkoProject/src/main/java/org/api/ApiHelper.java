package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.dto.responseDto.PostsDto;
import org.data.TestData;
import org.json.JSONObject;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());
    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse getAllPostsByUserRequest(String userName, int expectedStatusCode) {
        return given()
//                .contentType(ContentType.JSON)
//                .log().all()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POSTS_BY_USER, userName)
                .then()
                .log().all()
                .statusCode(expectedStatusCode);
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
//                .log().all()
                .spec(responseSpecification)
                .extract().body().asString().replaceAll("\"", "");
    }

    public PostsDto[] getAllPostsByUserInObject() {
        return getAllPostsByUserInObject(TestData.VALID_USERNAME_API, HttpStatus.SC_OK);
    }

    public PostsDto[] getAllPostsByUserInObject(String userName, int status) {
        return getAllPostsByUserRequest(userName, status)
                .extract().body().as(PostsDto[].class);
    }

    public void deleteAllPostsTillPresent(String validUsernameApi, String actualToken) {
        PostsDto[] listOfPosts = this.getAllPostsByUserInObject(validUsernameApi, HttpStatus.SC_OK);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(actualToken, listOfPosts[i].getId());
            logger.info(
                    String.format("Post with id %s and title %s was deleted.",
                    listOfPosts[i].getId(), listOfPosts[i].getTitle()));
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
}
