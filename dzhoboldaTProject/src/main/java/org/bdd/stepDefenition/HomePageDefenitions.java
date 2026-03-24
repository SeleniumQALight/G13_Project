package org.bdd.stepDefenition;

import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;

public class HomePageDefenitions  extends  MainSteps{
    public  HomePageDefenitions(WebDriverHelper webDriverHelper){
        super(webDriverHelper);
    }

    @Then("I see avatar on HomePage")
    public void i_see_avatar_on_home_page() {
        pageProvider.getHomePage().checkIsButtonSignOutVisible();
        // Write code here that turns the phrase above into concrete actions

    }
}
