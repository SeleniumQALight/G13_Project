package org.apiTests;

import org.api.PrivatApiHelper;
import org.api.dto.responseDto.ExchangeRateDto;
import org.api.dto.responseDto.PrivatExchangeDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class PrivatApiTests {
    PrivatApiHelper privatApiHelper = new PrivatApiHelper();
    String date = "22.03.2022";

    @Test
    public void getPrivatExchangeRateTest() {
        PrivatExchangeDto actualResponse = privatApiHelper.getExchangeRateByDateRequest(date)
                .extract().body().as(PrivatExchangeDto.class);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponse.getDate()).isEqualTo(date);
        softAssertions.assertThat(actualResponse.getBank()).isEqualTo("PB");
        softAssertions.assertThat(actualResponse.getBaseCurrency()).isEqualTo(980);
        softAssertions.assertThat(actualResponse.getBaseCurrencyLit()).isEqualTo("UAH");

        softAssertions.assertThat(actualResponse.getExchangeRate()).isNotEmpty();

        for (ExchangeRateDto rate : actualResponse.getExchangeRate()) {
            softAssertions.assertThat(rate.getBaseCurrency())
                    .as("Base currency in rate " + rate.getCurrency()) // для кращого тексту помилки
                    .isEqualTo("UAH");

            softAssertions.assertThat(rate.getCurrency())
                    .as("Currency field should not be null")
                    .isNotNull();
        }

        softAssertions.assertAll();
    }
}
