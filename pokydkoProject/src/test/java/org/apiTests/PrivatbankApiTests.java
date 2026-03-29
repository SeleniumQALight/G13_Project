package org.apiTests;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.ApiHelperPrivatbank;
import org.api.dto.responseDto.ExchangeRateDto;
import org.api.dto.responseDto.ExchangeRateResponseDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PrivatbankApiTests {

    Logger logger = Logger.getLogger(getClass());
    ApiHelperPrivatbank apiHelperPrivatbank = new ApiHelperPrivatbank();

    @Test
    public void getExchangeRatesByDateTest() {
        String testDate = "22.03.2022";

        ExchangeRateResponseDto actualResponse = apiHelperPrivatbank.getExchangeRatesByDate(testDate, HttpStatus.SC_OK)
        .extract().body().as(ExchangeRateResponseDto.class);

        logger.info("Actual response: " + actualResponse);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponse.getDate()).isEqualTo(testDate);
        softAssertions.assertThat(actualResponse.getBank()).isEqualTo("PB");
        softAssertions.assertThat(actualResponse.getBaseCurrency()).isEqualTo(980);
        softAssertions.assertThat(actualResponse.getBaseCurrencyLit()).isEqualTo("UAH");

        List<ExchangeRateDto> actualCurrencies = new ArrayList<>();

        for (int i = 0; i < actualResponse.getExchangeRate().size(); i++) {
            ExchangeRateDto rate = actualResponse.getExchangeRate().get(i);
            actualCurrencies.add(new ExchangeRateDto(rate.getBaseCurrency(), rate.getCurrency()));
        }

        List<ExchangeRateDto> expectedCurrencies = List.of(
                new ExchangeRateDto("UAH", "AUD"),
                new ExchangeRateDto("UAH", "AZN"),
                new ExchangeRateDto("UAH", "BYN"),
                new ExchangeRateDto("UAH", "CAD"),
                new ExchangeRateDto("UAH", "CHF"),
                new ExchangeRateDto("UAH", "CNY"),
                new ExchangeRateDto("UAH", "CZK"),
                new ExchangeRateDto("UAH", "DKK"),
                new ExchangeRateDto("UAH", "EUR"),
                new ExchangeRateDto("UAH", "GBP"),
                new ExchangeRateDto("UAH", "GEL"),
                new ExchangeRateDto("UAH", "HUF"),
                new ExchangeRateDto("UAH", "ILS"),
                new ExchangeRateDto("UAH", "JPY"),
                new ExchangeRateDto("UAH", "KZT"),
                new ExchangeRateDto("UAH", "MDL"),
                new ExchangeRateDto("UAH", "NOK"),
                new ExchangeRateDto("UAH", "PLN"),
                new ExchangeRateDto("UAH", "SEK"),
                new ExchangeRateDto("UAH", "SGD"),
                new ExchangeRateDto("UAH", "TMT"),
                new ExchangeRateDto("UAH", "TRY"),
                new ExchangeRateDto("UAH", "UAH"),
                new ExchangeRateDto("UAH", "USD"),
                new ExchangeRateDto("UAH", "UZS")
        );

        softAssertions.assertThat(actualCurrencies).usingRecursiveComparison()
                .ignoringFields("saleRateNB", "purchaseRateNB", "saleRate", "purchaseRate")
                .isEqualTo(expectedCurrencies);

        softAssertions.assertAll();
    }



}
