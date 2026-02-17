package org.pages;

import com.mysql.cj.log.Log;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.data.User;
import org.junit.Assert;
import org.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utils.Utils_Custom;

import java.util.List;

import static org.data.RegistrationValidationMessages.SEMICOLON;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement textInvalidMessage;
    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement inputUserNameRagistrationForm;

    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement inputEmailRagistrationForm;

    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement inputPasswordRagistrationForm;

    final static String listOfActualMessagesLocator
            = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = listOfActualMessagesLocator)
    private List<WebElement> listOfActualMessages;
    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement signUpButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened by url " + baseUrl);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputLogin(String text) {
//        WebElement inputLogin = webDriver.findElement(
//                By.xpath("//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys(text);
//        logger.info(text + " was entered in input Username");
        clearAndEnterTextIntoElement(inputLogin, text);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputPasswort(String text) {
        clearAndEnterTextIntoElement(inputPassword, text);
        return this;
    }

    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    @Step
    public void checksInvalidMessageVisible() {
        checksElementVisible(textInvalidMessage, "Validation error message");
        logger.info("Text InvalidMessage is visible");
    }

    @Step
    public LoginPage checkLoginAndPasswordInputsAreVisible() {
        checksElementVisible(inputLogin, "Login");
        checksElementVisible(inputPassword, "Password");
        return this;
    }

    @Step
    public LoginPage checkLoginAndPasswordInputsAreNotVisible() {
        checksElementNotVisible(inputLogin, "Login");
        checksElementNotVisible(inputPassword, "Password");

//        logger.info("Login and Password inputs are NOT visible");
        return this;
    }

    @Step
    public LoginPage checksButtonSignInVisible() {
        checksElementVisible(buttonSignIn, "Sign In");
        logger.info("Button SingIn is visible");
        return this;
    }

    @Step
    public HomePage openLoginPageAndFillFormWithValidCred() {
        this.openLoginPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextIntoInputPasswort(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextIntoElement(inputUserNameRagistrationForm, userName);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailRagistrationForm, email);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordRagistrationForm, password);
        return this;
    }

    @Step
    public LoginPage checkErrorMessages(String expectedMessages) {
        // error1;error2;error3 -> array [error1, error2, error3]
        String[] expectedMessagesArray = expectedMessages.split(SEMICOLON);

        webDriverWait10.withMessage("Number of error messages is not expected").until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualMessagesLocator),
                expectedMessagesArray.length));

        Utils_Custom.waitABit(1);

        Assert.assertEquals("Number of error messages ",
                expectedMessagesArray.length, listOfActualMessages.size());

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedMessagesArray.length; i++) {
            softAssertions
                    .assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number " + (i + 1))
                    .isIn(expectedMessagesArray);
        }

        softAssertions.assertAll();
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputLoginUsingActions(String username) {
        focusOnElementViaTab();
        clearAndEnterTextIntoElement(inputLogin, username);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputPasswordUsingActions(String password) {
        focusOnElementViaTab();
        clearAndEnterTextIntoElement(inputPassword, password);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationUserNameFieldUsingActions(String username) {
        focusOnElementViaTab();
        clearAndEnterTextIntoElement(inputUserNameRagistrationForm, username);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailFieldUsingActions(String email) {
        focusOnElementViaTab();
        clearAndEnterTextIntoElement(inputEmailRagistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordFieldUsingActions(String password) {
        focusOnElementViaTab();
        clearAndEnterTextIntoElement(inputPasswordRagistrationForm, password);
        return this;
    }

    public void pressEnterKeyOnSignIn() {
        pressEnterKey(buttonSignIn);
    }

    public LoginPage pressEnterKeyOnRegistrationForm() {
        pressEnterKey();
        return this;
    }

    public LoginPage enterRegistrationDataIfNotNull(User userData) {
        if (userData.getUserName() != null) {
            enterTextIntoRegistrationUserNameField(userData.getUserName());
        }
        if (userData.getEmail() != null) {
            enterTextIntoRegistrationEmailField(userData.getEmail());
        }
        if (userData.getPassword() != null) {
            enterTextIntoRegistrationPasswordField(userData.getPassword());
        }
        return this;
    }

    public void clickSignUpButton() {
        clickOnElement(signUpButton);
    }
}