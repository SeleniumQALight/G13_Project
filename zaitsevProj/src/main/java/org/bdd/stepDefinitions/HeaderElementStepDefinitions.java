package org.bdd.stepDefinitions;

import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;

public class HeaderElementStepDefinitions extends MainSteps{
    public HeaderElementStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }
    @When("I click on button MyProfile on Header Element")
    public void i_click_on_button_my_profile_on_header_element() {
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonMyProfile();
    }
}
