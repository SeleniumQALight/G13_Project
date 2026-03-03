package org.apiTests;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.api.EndPoints.POSTS_BY_USER;

public class ApiTests extends BaseTestApi {
    String sharedUserName = "qataras";

    @Test
    public void getAllPostsByUser(){
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(POSTS_BY_USER, sharedUserName) //TODO URL
                .then()
                .statusCode(200)
                .log().all();
    }
}

