package org.bdd.stepDefinitions;

import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.api.dto.helpers.PrivatBankApiHelper;
import org.data.CurrencyTestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.PrivatBankHomePage;

public class CurrencyStepDefinitions {

    private Logger logger = Logger.getLogger(getClass());
    private PrivatBankApiHelper apiHelper = new PrivatBankApiHelper();
    private WebDriver driver;

    @Given("I get currency rate from API for {}")
    public void getCurrencyFromApi(String currency) {

        double apiRate = apiHelper.getCurrencySaleRate(currency);
        CurrencyTestData.apiRates.put(currency, apiRate);
        logger.info("API rate for " + currency + " " + apiRate);
    }

    @When("I open PrivatBank UI and get rate for {}")
    public void getCurrencyFromUi(String currency) {

        driver = new ChromeDriver();
        PrivatBankHomePage homePage = new PrivatBankHomePage(driver);
        homePage.open();
        homePage.openExchangeRates();

        double uiRate = homePage.getSellRate(currency);
        CurrencyTestData.uiRates.put(currency, uiRate);
        logger.info("UI rate for " + currency + " " + uiRate);

        driver.quit();
    }

    @Then("I compare API and UI rates for {}")
    public void compareRates(String currency) {

        double apiRate = CurrencyTestData.apiRates.get(currency);
        double uiRate = CurrencyTestData.uiRates.get(currency);

        logger.info("Comparing API and UI rates for " + currency +
                ": API = " + apiRate + ", UI = " + uiRate);

        Assert.assertEquals(apiRate, uiRate, 0.5);
    }
}