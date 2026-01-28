package org.pages;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.data.RegistrationValidationMessages;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderForLoggedUserElement;
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

    @FindBy(xpath = "//div[text()='Invalid username/password.']")
    private WebElement errorMessageInvalidCred;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistrationForm;

    final static String listOfActualMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy
    (xpath = listOfActualMessagesLocator)
    private List<WebElement> listOfActualMessages;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }


    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login Page was Opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String text) {
       /* WebElement inputLogin = webDriver.findElement(
                By.xpath("//input[@placeholder='Username']"));*/
       /* inputLogin.clear();
        inputLogin.sendKeys(text);
        logger.info(text + " was entered in input UserName");*/
    clearAndEnterTextIntoElement(inputLogin, text);
    return this;
    }

    public LoginPage enterTextIntoInputPassword(String text) {
    clearAndEnterTextIntoElement(inputPassword, text);
    return this;
    }

    public HomePage clickOnButtonSignIn() {
        //webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        /*buttonSignIn.click();
        logger.info("Button SignIn was clicked");*/
        clickOnElement(buttonSignIn);
        return new HomePage(webDriver);
    }

    public LoginPage checkIsButtonSignInVisible(){
        checkIsElementEnabled(buttonSignIn);
        return this;
    }

    public void checkIsErrorMessageInvalidCredVisible(){
        checkIsElementEnabled(errorMessageInvalidCred);
    }

    public void checkIsNotInputLoginVisible(){
        checkIsNotElementVisible(inputLogin);
    }

    public LoginPage checkIsInputLoginVisible(){
        checkIsElementEnabled(inputLogin);
        return this;
    }


    public void checkIsNotInputPasswordVisible(){
        checkIsNotElementVisible(inputPassword);
    }

    public LoginPage checkIsInputPasswordVisible(){
        checkIsElementEnabled(inputPassword);
        return this;
    }


    public HomePage openLoginPageAndFillLoginFormWithValidCred(){
        this.openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage checkIsRedirectToLoginPage() {
        checkUrl();
        //TODO check some unique element
        return this;
    }


    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextIntoElement(inputUserNameRegistrationForm, userName);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedMessages) {
        String[] expectedErrorsArray = expectedMessages.split(SEMICOLON);

        webDriverWait10.withMessage("Number of error messages is not as expected")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualMessagesLocator),
                expectedErrorsArray.length));

        Utils_Custom.waitABit(1); //wait a bit to make sure all messages are loaded

        Assert.assertEquals("Number of error messages is not as expected",
                expectedErrorsArray.length,
                listOfActualMessages.size());

        SoftAssertions softAssertions = new SoftAssertions(); //create object for collection assertions

        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions
                    .assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number " + (i + 1) + " is not as expected")
                    .isIn(expectedErrorsArray);
        }

        softAssertions.assertAll(); //method to trigger all assertions

        return this;
    }
}
