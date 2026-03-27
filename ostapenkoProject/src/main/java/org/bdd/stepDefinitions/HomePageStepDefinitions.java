package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class HomePageStepDefinitions extends MainSteps {
    public HomePageStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open Home page as {string} user and {string} password")
    public void i_open_home_page_as_user_and_password(String username, String password) {
        if (MainSteps.DEFAULT.equalsIgnoreCase(username)) {
            username = TestData.VALID_LOGIN_API;
        }
        if (MainSteps.DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_API;
        }

        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(username)
                .enterTextIntoInputPasswort(password)
                .clickOnButtonSignIn();

        pageProvider.getHomePage()
                .checkIsRedirectToHomePage();
    }

    @Then("I see avatar on Home page")
    public void i_see_avatar_on_home_page() {
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checksAvatarVisible();
    }

}