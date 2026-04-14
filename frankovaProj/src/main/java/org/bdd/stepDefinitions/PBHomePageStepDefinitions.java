package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.bdd.helpers.WebDriverHelper;
import org.pages.PageProvider;

import static org.data.TestData.buyRateUI;
import static org.data.TestData.saleRateUI;

public class PBHomePageStepDefinitions extends PBMainSteps {
    private Logger logger= Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    public PBHomePageStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);

        this.pageProvider = new PageProvider(webDriverHelper.getWebDriver());
    }

    @And("I open PB Home page")
    public void iOpenPBHomePage() {
        pageProvider.getpbHomePage().openPBHomePage();

        logger.info("PB Home page was opened");
    }

    @When("I see {string} rates on the Home page")
    public void iSeeCurrencyRatesOnTheHomePage(String currency) {
        pageProvider.getpbHomePage().getCurrencyRatesTodayUI(currency);

        logger.info(String.format("UI exchange rates for " +currency + " = buy %s, sell %s",
                buyRateUI, saleRateUI));
    }
}
