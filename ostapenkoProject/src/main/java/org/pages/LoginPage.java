package org.pages;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
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

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened by url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String text) {
//        WebElement inputLogin = webDriver.findElement(
//                By.xpath("//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys(text);
//        logger.info(text + " was entered in input Username");
        clearAndEnterTextIntoElement(inputLogin, text);
        return this;
    }

    public LoginPage enterTextIntoInputPasswort(String text) {
        clearAndEnterTextIntoElement(inputPassword, text);
        return this;
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void checksInvalidMessageVisible() {
        checksElementVisible(textInvalidMessage, "Validation error message");
        logger.info("Text InvalidMessage is visible");
    }


    public LoginPage checkLoginAndPasswordInputsAreVisible() {
        checksElementVisible(inputLogin, "Login");
        checksElementVisible(inputPassword, "Password");
        return this;
    }

    public LoginPage checkLoginAndPasswordInputsAreNotVisible() {
        checksElementNotVisible(inputLogin, "Login");
        checksElementNotVisible(inputPassword, "Password");

//        logger.info("Login and Password inputs are NOT visible");
        return this;
    }

    public LoginPage checksButtonSignInVisible() {
        checksElementVisible(buttonSignIn, "Sign In");
        logger.info("Button SingIn is visible");
        return this;
    }

    public HomePage openLoginPageAndFillFormWithValidCred() {
        this.openLoginPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextIntoInputPasswort(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextIntoElement(inputUserNameRagistrationForm, userName);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailRagistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordRagistrationForm, password);
        return this;
    }

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

    public LoginPage enterTextIntoInputLoginUsingActions(String username) {
        moveViaTabAndEnterTextIntoElement(inputLogin, username);
        return this;
    }

    public LoginPage enterTextIntoInputPasswordUsingActions(String password) {
        moveViaTabAndEnterTextIntoElement(inputPassword, password);
        return this;
    }

    public LoginPage enterTextIntoRegistrationUserNameFieldUsingActions(String username) {
        moveViaTabAndEnterTextIntoElement(inputUserNameRagistrationForm, username);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailFieldUsingActions(String email) {
        moveViaTabAndEnterTextIntoElement(inputEmailRagistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordFieldUsingActions(String password) {
        moveViaTabAndEnterTextIntoElement(inputPasswordRagistrationForm, password);
        return this;
    }

    public void pressEnterKeyOnSignIn() {
        pressEnterKey(buttonSignIn);
    }

    public LoginPage pressEnterKeyOnRegistrationForm() {
        pressEnterKey();
        return this;
    }

}