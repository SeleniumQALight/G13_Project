package org.apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.api.ExchangeRateDTO;
import org.api.PrivatBankEndpoints;
import org.api.dto.responseDto.ExchangeRatesResponseDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class PrivatBankApiTest {

    SoftAssertions softAssertions = new SoftAssertions();
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void checkExchangeRatesFields() {

        String date = "22.03.2022";
        logger.info("Sending request to PrivatBank API with date: " + date);

        Response response =
                RestAssured
                        .given()
                        .baseUri(PrivatBankEndpoints.BASE_URL)
                        .queryParam("date", date)
                        .when()
                        .get(PrivatBankEndpoints.EXCHANGE_RATES)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        logger.info("Response received: " + response.asPrettyString());

        ExchangeRatesResponseDTO responseDTO =
                response.as(ExchangeRatesResponseDTO.class);

        logger.info("Checking main response fields");

        softAssertions.assertThat(responseDTO.getDate()).isEqualTo(date);
        softAssertions.assertThat(responseDTO.getBank()).isEqualTo("PB");
        softAssertions.assertThat(responseDTO.getBaseCurrency()).isEqualTo(980);
        softAssertions.assertThat(responseDTO.getBaseCurrencyLit()).isEqualTo("UAH");

        for (ExchangeRateDTO rate : responseDTO.getExchangeRate()) {
            logger.info("Checking currency " + rate.getCurrency());

            softAssertions.assertThat(rate.getBaseCurrency()).isEqualTo("UAH");
            softAssertions.assertThat(rate.getCurrency())
                    .isIn("USD", "EUR", "GBP", "CHF", "PLN", "SEK", "XAU", "CAD");        }
        softAssertions.assertAll();
        logger.info("Test checkExchangeRatesFields finished successfully");

    }

    @Test
    public void checkRatesGreaterThanZero() {

        String date = "22.03.2022";
        logger.info("Checking that currency rates are greater than zero");


        Response response =
                RestAssured
                        .given()
                        .baseUri(PrivatBankEndpoints.BASE_URL)
                        .queryParam("date", date)
                        .when()
                        .get(PrivatBankEndpoints.EXCHANGE_RATES)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        ExchangeRatesResponseDTO responseDTO =
                response.as(ExchangeRatesResponseDTO.class);


        for (ExchangeRateDTO rate : responseDTO.getExchangeRate()) {
            logger.info("Checking rates for currency " + rate.getCurrency());

            if (rate.getSaleRateNB() != null) {
                softAssertions.assertThat(rate.getSaleRateNB()).isGreaterThan(0);
            }

            if (rate.getPurchaseRateNB() != null) {
                softAssertions.assertThat(rate.getPurchaseRateNB()).isGreaterThan(0);
            }

            if (rate.getSaleRate() != null) {
                softAssertions.assertThat(rate.getSaleRate()).isGreaterThan(0);
            }

            if (rate.getPurchaseRate() != null) {
                softAssertions.assertThat(rate.getPurchaseRate()).isGreaterThan(0);
            }
        }
        softAssertions.assertAll();
        logger.info("Test checkRatesGreaterThanZero finished successfully");

    }

}
