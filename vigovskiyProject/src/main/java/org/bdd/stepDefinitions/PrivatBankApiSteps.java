package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import org.api.ApiHelper;
import org.api.dto.responseDto.CurrencyRate;
import org.data.TestData;

import java.util.List;

public class PrivatBankApiSteps {

    private ApiHelper apiHelper = new ApiHelper();

    @Given("I get exchange rate for {string} from API")
    public void getRateFromApi(String currency) {

        List<CurrencyRate> response = apiHelper.getPrivatBankRates();

        CurrencyRate selected = response.stream()
                .filter(r -> r.getCcy().equalsIgnoreCase(currency))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Currency not found: " + currency));

        TestData.apiBuyRate = selected.getBuy();
        TestData.apiSaleRate = selected.getSale();

        System.out.println("API BUY: " + TestData.apiBuyRate);
        System.out.println("API SALE: " + TestData.apiSaleRate);
    }
}