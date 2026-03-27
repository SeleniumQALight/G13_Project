package org.bdd.stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class HomePageStepDefinitions extends MainSteps{

    public HomePageStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on HomePage")
    public void i_see_avatar_on_home_page() {
  pageProvider.getHomePage().getHeaderForLoggedUserElement().checkIsAvatarIconVisible();
    }

    @Given("I open Home page as {string} user and {string} password")
    public void i_open_home_page_as_user_and_password(String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.VALID_USERNAME_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD_API;
        }

        pageProvider
                .getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(userName)
                .enterTextIntoInputPassword(password)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsRedirectToHomePage();
    }

}
