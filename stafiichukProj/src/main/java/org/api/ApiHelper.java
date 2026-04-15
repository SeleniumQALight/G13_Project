package org.api;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.api.dto.PrivatBankEndPoints;
import org.api.dto.responseDto.ExchangeRatesResponseDto;
import org.api.dto.responseDto.PostsDto;
import org.data.TestData;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiHelper {

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
                .get(EndPoints.POSTS_BY_USER, userName) // URL
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .log().all();
    }

    public String getToken() {
        return getToken(TestData.VALID_USERNAME_API, TestData.VALID_PASSWORD_API);
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
                .extract().body().asString().replace("\"", "");
    }

    public PostsDto[] getAllPostsByUserInObject() {
        return getAllPostsByUserRequest(TestData.VALID_USERNAME_API, HttpStatus.SC_OK)
                .extract().body()
                .as(PostsDto[].class);
    }

    public ExchangeRatesResponseDto getExchangeRates(String date){
        return given()
                .spec(requestSpecification)
                .queryParam("date", date)
                .when()
                .get(PrivatBankEndPoints.EXCHANGE_RATES)
                .then()
                .spec(responseSpecification)
                .extract().body()
                .as(ExchangeRatesResponseDto.class);
    }
}