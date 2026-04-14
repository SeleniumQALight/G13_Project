package org.api;

import dev.failsafe.internal.util.Assert;
import io.restassured.response.ValidatableResponse;
import org.api.dto.responseDto.CurrencyRate;
import org.api.dto.responseDto.ExchangeRatesResponseDto;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PrivatBankApiHelper {

    public ValidatableResponse getExchangeRates(String date) {
        return given()
                .queryParam("date", date)
                .log().all()
                .when()
                .get(PrivatBankEndPoints.EXCHANGE_RATES)
                .then()
                .statusCode(200);
    }

    public ExchangeRatesResponseDto getExchangeRatesDto(String date) {
        return getExchangeRates(date)
                .extract()
                .body()
                .as(ExchangeRatesResponseDto.class);
    }
}
