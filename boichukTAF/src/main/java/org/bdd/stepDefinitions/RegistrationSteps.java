package org.bdd.stepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import org.bdd.helpers.WebDriverHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.RegistrationPage;

import java.time.Duration;
import java.util.List;

public class RegistrationSteps {

    private WebDriverHelper webDriverHelper;
    private WebDriver driver;
    private RegistrationPage page;
    private WebDriverWait wait;

    public RegistrationSteps(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
    }

    @Before
    public void init() {
        driver = webDriverHelper.getWebDriver();
        page = new RegistrationPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Given("I open registration page")
    public void openPage() {
        page.open();
    }

    @When("I type username {string}")
    public void typeUsername(String username) {
        page.typeUsername(username);
    }

    @When("I type email {string}")
    public void typeEmail(String email) {
        page.typeEmail(email);
    }

    @When("I type password {string}")
    public void typePassword(String password) {
        page.typePassword(password);
    }

    @When("I click Sign Up button")
    public void clickSignUp() {
        page.clickSignUp();
    }

    @Then("I should see validation error {string}")
    public void checkError(String expectedError) {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//*[contains(@class,'liveValidateMessage')]")
        ));

        List<String> errors = page.getFieldErrors();
        errors.add(page.getGlobalError());

        System.out.println("Actual errors: " + errors);

        boolean found = errors.stream()
                .anyMatch(e -> e.contains(expectedError));

        Assert.assertTrue("Expected error not found: " + expectedError, found);
    }
}