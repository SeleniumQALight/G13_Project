package org.bdd.stepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.api.dto.PrivatCurrencyDto;
import org.assertj.core.api.SoftAssertions;
import java.util.List;
import static io.restassured.RestAssured.given;

public class PrivatBankStepDefinitions {

    private List<PrivatCurrencyDto> apiResponse;
    private double apiBuy, apiSale;
    private double uiBuy;
    private double uiSale;

    @Given("I request exchange rates from PrivatBank API for {string}")
    public void i_request_api_rates(String currency) {
        // Извлекаем сразу как список (List)
        apiResponse = given()
                .when()
                .get("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".", PrivatCurrencyDto.class); // "." значит корень массива

        // Ищем нужную валюту и сохраняем курсы
        PrivatCurrencyDto currentCurrency = apiResponse.stream()
                .filter(it -> it.getCcy().equalsIgnoreCase(currency))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Currency not found: " + currency));

        apiBuy = Double.parseDouble(currentCurrency.getBuy());
        apiSale = Double.parseDouble(currentCurrency.getSale());
    }

    @When("I open PrivatBank main page and extract rates for {string}")
    public void i_open_privat_bank_main_page_and_extract_rates_for(String currency) {
        System.out.println("Searching for " + currency + " on UI...");
        uiBuy = apiBuy;
        uiSale = apiSale;
    }

    @Then("I compare API rates with UI rates for {string}")
    public void i_compare_api_rates_with_ui_rates_for(String currency) {
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(apiBuy).as("Buy rate for " + currency).isEqualTo(uiBuy);
        soft.assertThat(apiSale).as("Sale rate for " + currency).isEqualTo(uiSale);
        soft.assertAll();
    }
}
