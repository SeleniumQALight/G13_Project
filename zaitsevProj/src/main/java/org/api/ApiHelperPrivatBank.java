package org.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatBank {

    public ValidatableResponse getExchangeRatesByDate(String date, int expectedStatusCode) {
        return given()
                .baseUri("https://api.privatbank.ua")
                .contentType(ContentType.JSON)
                .queryParam("date", date)
                .log().all()
                .when()
                .get(EndPointsPrivatBank.EXCHANGE_RATES)
                .then()
                .statusCode(expectedStatusCode)
                .log().all();
    }

    public ValidatableResponse getCashExchangeRates(int expectedStatusCode) {
        return given()
                .baseUri("https://api.privatbank.ua")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsPrivatBank.PUBLIC_INFO_ENDPOINT)
                .then()
                .statusCode(expectedStatusCode)
                .log().all();
    }
}
