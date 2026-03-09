package org.apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.EndPoints;
import org.api.EndPointsForPrivatBank;
import org.api.dto.ExchangeRateDto;
import org.api.dto.ExchangeResponseDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PrivatBankExchangeTest {

    Logger logger = Logger.getLogger(getClass());

    @Test
    public void checkExchangeRatesTest() {

        String date = "22.03.2022";

        logger.info("Send request to PrivatBank API");

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("date", date)
                        .when()
                        .get(EndPointsForPrivatBank.EXCHANGE_RATES)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .response();

        ExchangeResponseDto responseDto =
                response.as(ExchangeResponseDto.class);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(responseDto.getDate()).isEqualTo(date);
        softAssertions.assertThat(responseDto.getBank()).isEqualTo("PB");
        softAssertions.assertThat(responseDto.getBaseCurrency()).isEqualTo(980);
        softAssertions.assertThat(responseDto.getBaseCurrencyLit()).isEqualTo("UAH");

        for (ExchangeRateDto rate : responseDto.getExchangeRate()) {

            softAssertions.assertThat(rate.getBaseCurrency()).isEqualTo("UAH");
            softAssertions.assertThat(rate.getCurrency()).isNotNull();
        }

        softAssertions.assertAll();
    }

    @Test
    public void checkThatCurrencyRatesAreGreaterThanZero() {

        String date = "22.03.2022";

        logger.info("Check currency rates > 0");

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("date", date)
                        .when()
                        .get(EndPointsForPrivatBank.EXCHANGE_RATES)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .response();

        ExchangeResponseDto responseDto = response.as(ExchangeResponseDto.class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (ExchangeRateDto rate : responseDto.getExchangeRate()) {

            softAssertions.assertThat(rate.getSaleRateNB()).isGreaterThan(0);
            softAssertions.assertThat(rate.getPurchaseRateNB()).isGreaterThan(0);

            if (rate.getSaleRate() != null) {
                softAssertions.assertThat(rate.getSaleRate()).isGreaterThan(0);
            }

            if (rate.getPurchaseRate() != null) {
                softAssertions.assertThat(rate.getPurchaseRate()).isGreaterThan(0);
            }
        }

        softAssertions.assertAll();
    }
}
