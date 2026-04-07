package org.api.dto.helpers;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.api.CurrencyDto;

import java.util.List;

public class PrivatBankApiHelper {
    private static final String BASE_URI = "https://api.privatbank.ua";

    public double getCurrencySaleRate(String currency) {

        Response response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .when()
                .get("/p24api/pubinfo?json&exchange&coursid=5")
                .then()
                .statusCode(200)
                .extract().response();

        List<CurrencyDto> rates = response.jsonPath().getList("", CurrencyDto.class);

        for (CurrencyDto rate : rates) {
            if (rate.getCcy().equals(currency)) {
                return Double.parseDouble(rate.getSale());
            }
        }

        throw new RuntimeException("Currency not found: " + currency);
    }
}
