package org.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatbank {

    public ValidatableResponse getExchangeRatesByDate(String date, int expectedStatusCode) {
        return given()
                .baseUri(EndPointsPrivatbank.BASE_URL)
                .contentType(ContentType.JSON)
                .queryParam("date", date)
                .log().all()
                .when()
                .get(EndPointsPrivatbank.EXCHANGE_RATES)
                .then()
                .statusCode(expectedStatusCode)
                .log().all();
    }
}
