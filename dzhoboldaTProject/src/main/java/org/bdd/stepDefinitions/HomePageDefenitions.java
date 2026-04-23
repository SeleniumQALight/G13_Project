package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class HomePageDefenitions  extends  MainSteps{
    public  HomePageDefenitions(WebDriverHelper webDriverHelper){
        super(webDriverHelper);
    }

    @Given("I open Home page as {string} user and {string} password")
    public void i_open_home_page_as_user_and_password(String userName, String password) {
        if (MainSteps.DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_USERNAME_API;
        }
        if (MainSteps.DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_API;

        }
        pageProvider
                .getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(userName)
                .enterTextIntoInputPassword(password)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().checkRedirectToHomePage();
    }

    @Then("I see avatar on HomePage")
    public void i_see_avatar_on_home_page() {
        pageProvider.getHomePage().checkIsButtonSignOutVisible();
        // Write code here that turns the phrase above into concrete actions

    }
}
