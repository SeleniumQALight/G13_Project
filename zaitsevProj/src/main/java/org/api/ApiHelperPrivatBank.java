package org.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatBank {

    public ValidatableResponse getExchangeRatesByDate(String date, int expectedStatusCode) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("date", date)
                .log().all()
                .when()
                .get(EndPointsPrivatBank.EXCHANGE_RATES)
                .then()
                .statusCode(expectedStatusCode)
                .log().all();
    }
}
