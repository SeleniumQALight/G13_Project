package org.bdd.stepDefinitions;

import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.api.ApiHelper;
import org.api.ApiHelperPrivatbank;
import org.data.TestData;
import org.pages.HomePage;
import org.bdd.helpers.WebDriverHelper;
import org.junit.Assert;
import org.pages.PrivatBankHomePage;

import java.util.Map;

public class PrivatBankHomePageStepDefinitions {

    ApiHelper apiHelper = new ApiHelper();
    Logger logger = Logger.getLogger(getClass());
    PrivatBankHomePage privatBankPage =
            new PrivatBankHomePage(WebDriverHelper.getWebDriver());
    private ApiHelperPrivatbank apiHelperPrivatbank;

    @Given("I get rates for {string} from API")
    public void getRatesFromApi(String currency) {

        TestData.currency = currency;

        Map<String, String> rate = apiHelperPrivatbank.getCurrencyRate(currency);

        TestData.apiBuy = Double.parseDouble(rate.get("buy"));
        TestData.apiSale = Double.parseDouble(rate.get("sale"));
    }

    @When("I open home page")
    public void openHomePage() {
        privatBankPage.openPage();
    }

    @And("I get rates for {string} from UI")
    public void getRatesFromUI(String currency) {

        privatBankPage.openExchangeRates();
        TestData.uiBuy = privatBankPage.getBuyRate(currency);
        TestData.uiSale = privatBankPage.getSaleRate(currency);
        double uiRate = privatBankPage.getSaleRate(currency);
        TestData.uiRates.put(currency, uiRate);
        logger.info("UI rate for " + currency + " " + uiRate);
    }

    @Then("API and UI rates should match")
    public void compareRates() {

        double delta = 0.1;

        Assert.assertEquals(TestData.apiBuy, TestData.uiBuy, delta);
        Assert.assertEquals(TestData.apiSale, TestData.uiSale, delta);
    }
}
