package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.api.CurrencyDto;
import org.data.CurrencyTestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CurrencyStepDefinitions {

    private Logger logger = Logger.getLogger(getClass());

    @Given("I get currency rate from API for {}")
    public void getCurrencyFromApi(String currency) {
        // TODO: Move base URI to config
        Response response = RestAssured
                .given()
                .baseUri("https://api.privatbank.ua")
                .when()
                .get("/p24api/pubinfo?json&exchange&coursid=5")
                .then()
                .statusCode(200)
                .extract().response();

        List<CurrencyDto> rates = response.jsonPath().getList("", CurrencyDto.class);

        for (CurrencyDto rate : rates) {
            if (rate.getCcy().equals(currency)) {
                double apiRate = Double.parseDouble(rate.getSale());
                CurrencyTestData.apiRates.put(currency, apiRate);
                logger.info("API rate for " + currency + " " + apiRate);
            }
        }
    }

    @When("I open PrivatBank UI and get rate for {}")
    public void getCurrencyFromUi(String currency) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://privatbank.ua/");
        // TODO: Move locators to separate class
        driver.findElement(By.xpath("//li//button[@class='btn exchange-rate']")).click();

        double rate = 0;

        if (currency.equals("USD")) {
            rate = Double.parseDouble(driver.findElement(By.xpath("//td[@id='USD_sell']")).getText());
        }

        if (currency.equals("EUR")) {
            rate = Double.parseDouble(driver.findElement(By.xpath("//td[@id='EUR_sell']")).getText());
        }

        CurrencyTestData.uiRates.put(currency, rate);
        logger.info("API rate for " + currency + " " + rate);

    }

    @Then("I compare API and UI rates for {}")
    public void compareRates(String currency) {
        double apiRate = CurrencyTestData.apiRates.get(currency);
        double uiRate = CurrencyTestData.uiRates.get(currency);
        logger.info("Comparing API and UI rates for " + currency + ": API = " + apiRate + ", UI = " + uiRate);
        Assert.assertEquals(apiRate, uiRate, 0.5);
    }

}
