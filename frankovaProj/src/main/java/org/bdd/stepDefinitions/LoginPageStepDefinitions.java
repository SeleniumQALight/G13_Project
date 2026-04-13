package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class LoginPageStepDefinitions extends MainSteps{
    public LoginPageStepDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open Login page")
    public void iOpenLoginPage(){
        pageProvider.getLoginPage().openLoginPage();

    }

    @When("I login with valid cred")
    public void iLoginWithValidCred() {
        pageProvider.getLoginPage()
                .enterTextIntoInputLogin(TestData.VALID_LOGIN)
                .enterTextIntoInputPassword(TestData.VALID_PASSWORD)
                .clickOnButtonSignIn();
    }


    @When("I enter {string} into input Login in Login page")
    public void iEnterLoginIntoInputLoginInLoginPage(String userName) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(userName);
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
    pageProvider.getLoginPage().checkTextInAlertInCenter(errorMessage);
    }

    @When("I enter {string} into Registration UserName Field in Login page")
    public void iEnterUsernameIntoRegistrationUserNameFieldInLoginPage(String username) {
       pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(username);
    }

    @And("I enter {string} into Registration Email Field in Login page")
    public void iEnterEmailIntoRegistrationEmailFieldInLoginPage(String email) {
        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);
    }

    @And("I enter {string} into Registration Password Field in Login page")
    public void iEnterPasswordIntoRegistrationPasswordFieldInLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);
    }

    @Then("I see {string} Errors Messages")
    public void iSeeExpectedMessagesErrorsMessages(String expectedMessages) {
      pageProvider.getLoginPage().checkErrorsMessages(expectedMessages);
    }
}
