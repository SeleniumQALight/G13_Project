package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.PrivatBankPage;
import api.PrivatBankApi;

public class CurrencySteps {

    private WebDriver driver = DriverFactory.getDriver();
    private PrivatBankPage page = new PrivatBankPage(driver);

    private static double apiRate;
    private static double uiRate;
    private static String currency;

    @Given("I open PrivatBank home page")
    public void openHomePage() {
        page.openPage();
    }

    @When("I get {string} rates from API")
    public void getRatesFromAPI(String currency) {
        CurrencySteps.currency = currency;

        apiRate = PrivatBankApi.getRate(currency, "buy");

        System.out.println("API " + currency + " buy: " + apiRate);
    }

    @When("I get {string} rates from UI")
    public void getRatesFromUI(String currency) {
        page.openExchangeRates();

        String rateText = page.getBuyRate(currency);

        uiRate = Double.parseDouble(rateText.replace(",", "."));

        System.out.println("UI " + currency + " buy: " + uiRate);
    }

    @Then("I compare API and UI rates")
    public void compareRates() {
        System.out.println("Compare: API=" + apiRate + " UI=" + uiRate);

        Assert.assertEquals(
                "Rates do not match for " + currency,
                apiRate,
                uiRate,
                0.01
        );
    }
}