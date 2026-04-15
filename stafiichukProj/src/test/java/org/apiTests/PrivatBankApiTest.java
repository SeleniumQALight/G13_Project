package org.apiTests;

import org.api.ApiHelper;
import org.api.dto.responseDto.ExchangeRateDto;
import org.api.dto.responseDto.ExchangeRatesResponseDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class PrivatBankApiTest extends BaseTestPrivatApi {

    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void getExchangeRatesTest() {
        String testDate = "22.03.2022";

        ExchangeRatesResponseDto response =
                apiHelper.getExchangeRates(testDate);

        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(response.getDate()).isEqualTo(testDate);
        softAssert.assertThat(response.getBank()).isEqualTo("PB");
        softAssert.assertThat(response.getBaseCurrency()).isEqualTo(980);
        softAssert.assertThat(response.getBaseCurrencyLit()).isEqualTo("UAH");
        softAssert.assertThat(response.getExchangeRate()).isNotEmpty();

        for (ExchangeRateDto exchangeRate : response.getExchangeRate()) {
            softAssert.assertThat(exchangeRate.getBaseCurrency()).isNotBlank();
            softAssert.assertThat(exchangeRate.getCurrency()).isNotBlank();
        }
        softAssert.assertAll();

    }
}