package org.apiTests;

import io.restassured.RestAssured;
import org.api.EndPoints;
import org.api.dto.PrivatExchangeDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;



public class PrivatBankTests extends BaseTestApi {

    @Test
    public void getPrivatExchangeRatesTest() {
        String date = "22.03.2022";
        SoftAssertions softAssertions = new SoftAssertions();

        PrivatExchangeDto response = given()
                 .spec(getRequestSpecification())
                .queryParam("date", date)
                .when()
                .get(EndPoints.PRIVAT_EXCHANGE_RATES)
                .then()
                .statusCode(200)
                .extract().as(PrivatExchangeDto.class);

        softAssertions.assertThat(response.getDate()).isEqualTo(date);
        softAssertions.assertThat(response.getBank()).isEqualTo("PB");
        softAssertions.assertThat(response.getBaseCurrencyLit()).isEqualTo("UAH");

        // Переделанный цикл
        response.getExchangeRate().forEach(rate -> {
            softAssertions.assertThat(rate.getBaseCurrency())
                    .as("Base currency for " + rate.getCurrency())
                    .isEqualTo("UAH");

            //  название валюты (currency) не пустое
            softAssertions.assertThat(rate.getCurrency())
                    .as("Currency field is null")
                    .isNotNull();

            //  все курсы > 0

            validateRateGreaterThanZero(softAssertions, "saleRateNB", rate.getSaleRateNB());
            validateRateGreaterThanZero(softAssertions, "purchaseRateNB", rate.getPurchaseRateNB());
            validateRateGreaterThanZero(softAssertions, "saleRate", rate.getSaleRate());
            validateRateGreaterThanZero(softAssertions, "purchaseRate", rate.getPurchaseRate());
        });

//        // Цикл для проверки валют (baseCurrency и currency)
//        response.getExchangeRate().forEach(rate -> {
//            softAssertions.assertThat(rate.getBaseCurrency()).isEqualTo("UAH");
//            softAssertions.assertThat(rate.getCurrency()).isNotNull();
//        });
//

        softAssertions.assertAll();

    }


    private void validateRateGreaterThanZero(SoftAssertions softAssertions, String fieldName, Double value) {
        if (value != null) {
            softAssertions.assertThat(value)
                    .as("Field " + fieldName + " should be greater than 0, but was " + value)
                    .isGreaterThan(0);
    }
}
    }
