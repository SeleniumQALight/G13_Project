package org.apiTests;
import org.apache.hc.core5.http.HttpStatus;
import org.api.PBApiHelper;
import org.api.dto.responseDto.PBBaseCurrency;
import org.api.dto.responseDto.PBExchangeRatesDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;


public class PBApiTests extends PBBaseTestApi {

    SoftAssertions softAssertions = new SoftAssertions();

    PBApiHelper pbApiHelper = new PBApiHelper();

    String date = "22.03.2022";


    @Test
    public void getExchangeRatesByDatePositive() {

        PBBaseCurrency actualResult = PBApiHelper.getExchangeRatesByDate(date, HttpStatus.SC_OK)
                .extract().body().as(PBBaseCurrency.class);


        PBBaseCurrency expectedResult = new PBBaseCurrency(
                date, "PB", 980, "UAH", new PBExchangeRatesDto[]{
                new PBExchangeRatesDto("UAH", "AUD"),
                new PBExchangeRatesDto("UAH", "AZN"),
                new PBExchangeRatesDto("UAH", "BYN"),
                new PBExchangeRatesDto("UAH", "CAD"),
                new PBExchangeRatesDto("UAH", "CHF"),
                new PBExchangeRatesDto("UAH", "CNY"),
                new PBExchangeRatesDto("UAH", "CZK"),
                new PBExchangeRatesDto("UAH", "DKK"),
                new PBExchangeRatesDto("UAH", "EUR"),
                new PBExchangeRatesDto("UAH", "GBP"),
                new PBExchangeRatesDto("UAH", "GEL"),
                new PBExchangeRatesDto("UAH", "HUF"),
                new PBExchangeRatesDto("UAH", "ILS"),
                new PBExchangeRatesDto("UAH", "JPY"),
                new PBExchangeRatesDto("UAH", "KZT"),
                new PBExchangeRatesDto("UAH", "MDL"),
                new PBExchangeRatesDto("UAH", "NOK"),
                new PBExchangeRatesDto("UAH", "PLN"),
                new PBExchangeRatesDto("UAH", "SEK"),
                new PBExchangeRatesDto("UAH", "SGD"),
                new PBExchangeRatesDto("UAH", "TMT"),
                new PBExchangeRatesDto("UAH", "TRY"),
                new PBExchangeRatesDto("UAH", "UAH"),
                new PBExchangeRatesDto("UAH", "USD"),
                new PBExchangeRatesDto("UAH", "UZS")
        }
        );

        softAssertions.assertThat(actualResult)
                .usingRecursiveComparison()
                .ignoringFields(
                        "exchangeRate.saleRateNB",
                        "exchangeRate.purchaseRateNB",
                        "exchangeRate.saleRate",
                        "exchangeRate.purchaseRate"
                )
                .isEqualTo(expectedResult);

        softAssertions.assertAll();
    }


    @Test
    public void validateExchangeRatesAreGreaterThanZero() {

        PBBaseCurrency actualResult = PBApiHelper.getExchangeRatesByDate(date, HttpStatus.SC_OK)
                .extract().body().as(PBBaseCurrency.class);

        for (PBExchangeRatesDto rate : actualResult.getExchangeRate()) {
            softAssertions.assertThat(rate.getSaleRateNB())
                    .as("Sale rate NB for " + rate.getCurrency() + " is not greater than zero") //Метод .as() додає опис до перевірки
                    .isGreaterThan(0.0);

            softAssertions.assertThat(rate.getPurchaseRateNB())
                    .as("Purchase rate NB for " + rate.getCurrency() + " is not greater than zero")
                    .isGreaterThan(0.0);

            if (rate.getSaleRate() != null) {
                softAssertions.assertThat(rate.getSaleRate())
                        .as("Sale rate for " + rate.getCurrency() + " is not greater than zero")
                        .isGreaterThan(0.0);
            }

            if (rate.getPurchaseRate() != null) {
                softAssertions.assertThat(rate.getPurchaseRate())
                        .as("Purchase rate for " + rate.getCurrency() + " is not greater than zero")
                        .isGreaterThan(0.0);
            }


        }
    }
}
