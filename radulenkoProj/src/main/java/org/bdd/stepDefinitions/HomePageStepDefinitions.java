package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;

public class HomePageStepDefinitions extends MainSteps {
    public HomePageStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on HomePage")
    public void i_see_avatar_on_home_page() {
        pageProvider.getHomePage().checkIsButtonSignOutVisible();
    }
}
