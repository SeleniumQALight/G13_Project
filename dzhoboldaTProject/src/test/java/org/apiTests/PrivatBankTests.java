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

        // Цикл для проверки валют (baseCurrency и currency)
        response.getExchangeRate().forEach(rate -> {
            softAssertions.assertThat(rate.getBaseCurrency()).isEqualTo("UAH");
            softAssertions.assertThat(rate.getCurrency()).isNotNull();
        });
//

        softAssertions.assertAll();
    }
}
