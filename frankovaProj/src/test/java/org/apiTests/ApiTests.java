package org.apiTests;

import io.restassured.http.ContentType;
import org.api.EndPoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests extends BaseTestApi {

    String sharedUserName = "qataras";

    @Test
    public void getAllPostsByUser(){
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POSTS_BY_USER,sharedUserName)
                .then()
                        .statusCode(200)
                .log().all();

    }
}
