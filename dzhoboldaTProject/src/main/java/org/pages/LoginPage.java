package org.pages;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.data.RegistrationVadidationMessages;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utils.Utils_Custom;

import java.util.List;

import static org.data.RegistrationVadidationMessages.SEMICOLON;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(id = "username-register")  // xpath = ".//*[@id='username-register']"
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInRegistrationForm;

    final static String listOfActualMessagesLocator
            = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfActualMessages;


    @FindBy(xpath = "//div[@class='alert alert-danger text-center' and text()='Invalid username/password.']")
    private WebElement invalidLoginError;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }


    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String text) {
        clearAndEnterTextIntoElement(inputLogin, text);
        logger.info(text + " was entered in input UserName");
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String text) {
        clearAndEnterTextIntoElement(inputPassword, text);
        logger.info("Password was entered");
        return this;
    }


    public HomePage clickOnButtonSignIn() {
        try {
            logger.info("Clicking Sign In button");
            clickOnElement(buttonSignIn);
            logger.info("Sign In button was clicked");
            return new HomePage(webDriver);
        } catch (Exception e) {
            logger.error("Cannot click Sign In button", e);
            throw new RuntimeException("Cannot click Sign In button", e);
        }
    } //Checked this method for exception handling Accidently deleted the commented method above

    public HomePage openLoginPageAndFillLoginFormWithVailidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.ValidLogin);
        enterTextIntoInputPassword(TestData.ValidPassword);
        return clickOnButtonSignIn();
    }

    public LoginPage checkLoginInputVisible() {
        checkIsElementDisplayed(inputLogin);
        return this;
    }

    public LoginPage checkPasswordInputVisible() {
        checkIsElementDisplayed(inputPassword);
        return this;
    }

    public LoginPage checkSignInButtonVisible() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }




    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextIntoElement(inputUserNameRegistrationForm, userName);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailInRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordInRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedMessages) {
        //error1;error2;error3 ->
        String[] expectedErrorsArray = expectedMessages.split(SEMICOLON);
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualMessagesLocator),
                expectedErrorsArray.length));
        Utils_Custom.waitABit(1); // додатково чекаємо 1 секунду щоб всі повідомлення встигли з
        Assert.assertEquals("Number of messages ", expectedErrorsArray.length, listOfActualMessages.size());
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions
                    .assertThat(listOfActualMessages.get(i).getText())
                    .as("Message " + (i + 1))
                    .isIn(expectedErrorsArray);
        }

        softAssertions.assertAll();

        return this;
    }

    public LoginPage refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page was refreshed");
        return this;
    }


    public LoginPage checkInvalidLoginError() {
        Assert.assertEquals("Invalid username/password.", invalidLoginError.getText());
        return this;
    }

}

