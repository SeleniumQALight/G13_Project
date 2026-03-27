package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class LoginPageSteosDefenitions extends MainSteps {
    public LoginPageSteosDefenitions(WebDriverHelper webDriverHelper) {

        super(webDriverHelper);
    }

    @Given("I open Login page")
    public void iOpenLoginPage(){
        pageProvider.getLoginPage().openLoginPage();
        
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        pageProvider.getLoginPage()
                .enterTextIntoInputLogin(TestData.ValidLogin)
        .enterTextIntoInputPassword(TestData.ValidPassword)
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
        pageProvider.getLoginPage().checkTextInAlertinCenter(errorMessage);
    }
    @When("I enter {string} into input UserName in Registration form on Login page")
    public void iEnterUsernameIntoRegistrationForm(String userName) {
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(userName);
    }
//
//    @And("I enter {string} into input Email in Registration form on Login page")
//    public void iEnterEmailIntoRegistrationForm(String email) {
//        pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(email);
//    }

//    @And("I enter {string} into input Password in Registration form on Login page")
//    public void iEnterPasswordIntoRegistrationForm(String password) {
//        pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(password);
//    }

//    @Then("I see error messages {string} in Registration form")
//    public void iSeeErrorMessagesInRegistrationForm(String expectedMessages) {
//        //  метод с SoftAssert
//        pageProvider.getLoginPage().checkErrorsMessages(expectedMessages);
//    }
    @When("I enter {string} into {string} registration field")
    public void i_enter_into_registration_field(String value, String fieldName) {
        if (fieldName.equalsIgnoreCase("Username")) {
            pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(value);
        } else if (fieldName.equalsIgnoreCase("Email")) {
            pageProvider.getLoginPage().enterTextIntoRegistrationEmailField(value);
        } else if (fieldName.equalsIgnoreCase("Password")) {
            pageProvider.getLoginPage().enterTextIntoRegistrationPasswordField(value);
        }
    }

    @Then("I see error messages {string}")
    public void i_see_error_messages(String messages) {
        pageProvider.getLoginPage().checkErrorsMessages(messages);
    }
}
