package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class LoginPageStepDefinitions extends MainSteps {
    public LoginPageStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open Login page")
    public void iOpenLoginPage() {
        pageProvider.getLoginPage().openLoginPage();
    }

    @When("I login with valid cred")
    public void iLoginWithValidCred() {
            pageProvider.getLoginPage()
                    .enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
                    .enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI)
                    .clickOnButtonSignIn();
    }

    @When("I enter {string} into Login in Login page")
    public void iEnterIntoLoginInLoginPage(String userName) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(userName);
    }

    @And("I enter {string} into input PassWord in Login page")
    public void iEnterIntoInputPassWordInLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }

    @And("I click on button SignIn in Login page")
    public void iClickOnButtonSignInInLoginPage() {
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithText(String errorMessage) {
        pageProvider.getLoginPage().checkTextInAlertInCenter(errorMessage);
    }
}
