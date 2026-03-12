package org.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class PrivatApiHelper {
    public ValidatableResponse getExchangeRateByDateRequest(String date) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("date", date)
                .log().all()
                .when()
                .get(PrivatEndPoints.PRIVAT_EXCHANGE)
                .then()
                .log().all();
    }
}
