package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.api.ApiHelperPrivatBank;
import org.api.dto.responseDto.ExchangeRateDto;
import org.api.dto.responseDto.PrivatBankCurrencyDto;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;
import org.junit.Assert;

public class PrivatBankRatesStepDefinitions extends MainSteps {
    private Logger logger = Logger.getLogger(getClass());
    private final ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    public PrivatBankRatesStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get PrivatBank API rate for {string} currency")
    public void iGetPrivatBankApiRateForCurrency(String currency) {
        PrivatBankCurrencyDto[] response = apiHelperPrivatBank
                .getCashExchangeRates(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(PrivatBankCurrencyDto[].class);

        PrivatBankCurrencyDto neededCurrency = null;

        for (PrivatBankCurrencyDto currencyDto : response) {
            if (currencyDto.getCcy().equalsIgnoreCase(currency)) {
                neededCurrency = currencyDto;
                break;
            }
        }

        Assert.assertNotNull("Currency " + currency + " was not found in API response", neededCurrency);

        TestData.currentCurrency = currency.toUpperCase();
        TestData.apiBuyRate = Double.valueOf(neededCurrency.getBuy());
        TestData.apiSaleRate = Double.valueOf(neededCurrency.getSale());

        logger.info("Saved API rates for " + currency
                + ". Buy = " + TestData.apiBuyRate
                + ", Sale = " + TestData.apiSaleRate);
    }

    @When("I open PrivatBank home page")
    public void iOpenPrivatBankHomePage() {
        pageProvider.getPrivatBankHomePage().openPrivatBankHomePage();
    }

    @When("I save UI rate for current currency from PrivatBank home page")
    public void iSaveUiRateForCurrentCurrencyFromPrivatBankHomePage() {
        ExchangeRateDto uiRate = pageProvider.getPrivatBankHomePage()
                .getCashRateForCurrency(TestData.currentCurrency);

        TestData.uiBuyRate = uiRate.getPurchaseRate();
        TestData.uiSaleRate = uiRate.getSaleRate();

        logger.info("Saved UI rates for " + TestData.currentCurrency
                + ". Buy = " + TestData.uiBuyRate
                + ", Sale = " + TestData.uiSaleRate);
    }

    @Then("I compare API and UI rates for current currency")
    public void iCompareApiAndUiRatesForCurrentCurrency() {
        Assert.assertEquals(
                "Buy rate is not equal for currency " + TestData.currentCurrency,
                TestData.apiBuyRate,
                TestData.uiBuyRate,
                0.001
        );

        Assert.assertEquals(
                "Sale rate is not equal for currency " + TestData.currentCurrency,
                TestData.apiSaleRate,
                TestData.uiSaleRate,
                0.001
        );

        logger.info("API and UI rates are equal for currency " + TestData.currentCurrency);
    }
}
