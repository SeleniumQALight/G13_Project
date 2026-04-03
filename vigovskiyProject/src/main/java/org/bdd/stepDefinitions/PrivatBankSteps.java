package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.pages.PrivatBankPage;
import org.data.TestData;
import org.bdd.helpers.WebDriverHelper;

public class PrivatBankSteps {

    private WebDriverHelper webDriverHelper;
    private WebDriver driver;
    private PrivatBankPage privatBankPage;

    public PrivatBankSteps(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
        this.driver = webDriverHelper.getWebDriver();
        this.privatBankPage = new PrivatBankPage(driver);
    }


    @When("I get exchange rate for {string} from UI")
    public void getRateFromUI(String currency) {

        privatBankPage.openPage();

        privatBankPage.clickOnExchangeRates();

        if (currency.equalsIgnoreCase("USD")) {
            TestData.uiBuyRate = privatBankPage.getUsdBuy();
            TestData.uiSaleRate = privatBankPage.getUsdSale();
        } else if (currency.equalsIgnoreCase("EUR")) {
            TestData.uiBuyRate = privatBankPage.getEurBuy();
            TestData.uiSaleRate = privatBankPage.getEurSale();
        } else if (currency.equalsIgnoreCase("PLN")) {
            TestData.uiBuyRate = privatBankPage.getPlnBuy();
            TestData.uiSaleRate = privatBankPage.getPlnSale();
        }

        System.out.println("UI BUY: " + TestData.uiBuyRate);
        System.out.println("UI SALE: " + TestData.uiSaleRate);
    }

    @Then("I compare API rate with UI rate for {string}")
    public void compareRates(String currency) {

        Assert.assertEquals(
                normalize(TestData.apiBuyRate),
                normalize(TestData.uiBuyRate)
        );

        Assert.assertEquals(
                normalize(TestData.apiSaleRate),
                normalize(TestData.uiSaleRate)
        );

    }

    private String normalize(String rate) {
        return String.format("%.2f", Double.parseDouble(rate));
    }
}