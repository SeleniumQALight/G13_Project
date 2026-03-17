package org.apiTests;
import org.api.PrivatBankApiHelper;
import org.api.dto.responseDto.ExchangeRateDto;
import org.api.dto.responseDto.ExchangeRatesResponseDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.List;

public class PrivatBankApiTest extends PrivatBankBaseTest{
    PrivatBankApiHelper apiHelper = new PrivatBankApiHelper();

    @Test
    public void validateExchangeRatesFields() {
        String testDate = "22.03.2022";

        ExchangeRatesResponseDto response = apiHelper.getExchangeRatesDto(testDate);
        SoftAssertions softAssertions = new SoftAssertions();


        softAssertions.assertThat(response.getDate()).isEqualTo(testDate);
        softAssertions.assertThat(response.getBank()).isNotEmpty();
        softAssertions.assertThat(response.getBaseCurrency()).isGreaterThan(0);
        softAssertions.assertThat(response.getBaseCurrencyLit()).isNotEmpty();


        List<String> validCurrencies = List.of(
                "UAH", "USD", "EUR", "GBP", "CHF", "PLN", "SEK", "CAD", "XAU",
                "AUD", "AZN", "BYN", "CNY", "CZK", "DKK", "GEL", "HUF",
                "ILS", "JPY", "KZT", "MDL", "NOK", "SGD", "TMT", "TRY", "UZS"
        );


        List<ExchangeRateDto> rates = response.getExchangeRate();
        for (ExchangeRateDto rate : rates) {

            softAssertions.assertThat(rate.getBaseCurrency())
                    .as("Base currency should be valid")
                    .isIn(validCurrencies);

            if (rate.getCurrency() != null) {
                softAssertions.assertThat(rate.getCurrency())
                        .as("Currency should be valid")
                        .isIn(validCurrencies);
            }
        }

        softAssertions.assertAll();
    }

    @Test
    public void validateExchangeRatesPositiveValues() {
        String testDate = "22.03.2022";

        ExchangeRatesResponseDto response = apiHelper.getExchangeRatesDto(testDate);
        SoftAssertions softAssertions = new SoftAssertions();

        List<ExchangeRateDto> rates = response.getExchangeRate();
        for (ExchangeRateDto rate : rates) {
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
    }
}
