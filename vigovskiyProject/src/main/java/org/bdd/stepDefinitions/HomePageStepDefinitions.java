package org.bdd.stepDefinitions;

import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;

public class HomePageStepDefinitions extends MainSteps{
    public HomePageStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I see avatar on HomePage")
    public void i_see_avatar_on_home_page() {
       pageProvider.getHomePage().checkIsButtonSighOutVisible();
    }
}
