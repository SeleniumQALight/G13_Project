package org.apiTests;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests extends BaseTestApi {

    @Test
    public void getAllPostsByUser(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get() // URL
                .then()
                .statusCode(200);

    }

}
