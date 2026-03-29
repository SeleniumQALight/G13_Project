package org.api;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class PBApiHelper {
    public static ValidatableResponse getExchangeRatesByDate(String date, int expectedStatusCode) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("date", date)
                .log().all()
                .when()
                .get(PBEndPoints.EXCHANGE_RATES_BY_DATE)
                .then()
                .statusCode(expectedStatusCode)
                .log().all();
    }
}
