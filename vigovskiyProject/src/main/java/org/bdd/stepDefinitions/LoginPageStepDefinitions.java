package org.bdd.stepDefinitions;

import io.cucumber.java.PendingException;
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
                .enterTestIntoInputLogin(TestData.VALID_LOGIN)
                .enterTextIntoInputPassword(TestData.VALID_PASSWORD).clickOnButtonSignIn();

    }

    @When("I enter {string} into input Login in Login page")
    public void iEnterLoginIntoInputLoginInLoginPage(String userName) {
        pageProvider.getLoginPage().enterTestIntoInputLogin(userName);

    }

    @And("I enter {string} into input PassWord in Login page")
    public void iEnterPasswordIntoInputPassWordInLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);

    }

    @And("I click on button SignIn in Login page")
    public void iClickOnButtonSignInInLoginPage() {
        pageProvider.getLoginPage().clickOnButtonSignIn();

    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithText(String errorMessage) {
       pageProvider.getLoginPage().checkTextInAllertInCenter(errorMessage);
    }
}
