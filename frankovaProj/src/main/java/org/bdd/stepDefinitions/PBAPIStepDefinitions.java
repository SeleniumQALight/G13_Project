package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.ValidatableResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.PBApiHelper;
import org.api.dto.responseDto.PBExchangeRatesTodayDto;
import org.data.TestData;
import org.junit.Assert;

public class PBAPIStepDefinitions {
    private PBApiHelper pbApiHelper = new PBApiHelper();
    private Logger logger= Logger.getLogger(getClass());

    @Given("I get PB {string} rates via API") // Використовуйте {string} для параметрів
    public void iGetPBCurrencyRatesViaAPI(String currency) {
        ValidatableResponse response = PBApiHelper.getExchangeRatesForToday(HttpStatus.SC_OK);

        PBExchangeRatesTodayDto targetCurrency = response
                .extract()
                .jsonPath()
                .param("curr", currency)
                .getObject("find { it.ccy == curr }", PBExchangeRatesTodayDto.class);

        Double buyRateAPI = targetCurrency.getBuy();
        Double saleRateAPI = targetCurrency.getSale();

        TestData.buyRateAPI = buyRateAPI;
        TestData.saleRateAPI = saleRateAPI;

        logger.info("buy rate API for " + currency + " = " + buyRateAPI);
        logger.info("sale rate API for " + currency + " = " + saleRateAPI);
    }

    @Then("I compare {string} rates obtained via API and via UI")
    public void iCompareCurrencyRatesObtainedViaAPIAndViaUI(String currency) {

        Assert.assertEquals(
                String.format("buy rates %s are different", currency),
                TestData.buyRateAPI,
                TestData.buyRateUI
        );

        Assert.assertEquals(
                String.format("sale rates %s are different", currency),
                TestData.saleRateAPI,
                TestData.saleRateUI
        );

        logger.info("Exchange rates for " + currency + " are similar on UI and from API");
    }
    }
