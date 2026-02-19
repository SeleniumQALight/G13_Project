package org.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.data.RegistrationValidationMessages;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.utils.Utils_Custom;

import java.time.Instant;
import java.util.List;

import static org.data.RegistrationValidationMessages.SEMICOLON;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//div[text() = 'Invalid username/password.']")
    private WebElement invalidUsernamePasswordMessage;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement invalidLoginMessage;

    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement inputUserNameRegistrationForm;

    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement inputEmailRegistrationForm;

    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement inputPasswordRegistrationForm;

    final static String listOfActualMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listOfActualMessagesLocator)
    private List<WebElement> listOfActualMessages;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputLogin(String text) {
//    WebElement inputLogin = webDriver.findElement(
//            By.xpath("//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys(text);
//        logger.info(text + " was entered in input Username");
        clearAndEnterTextIntoElement(inputLogin, text);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputPassword(String text) {
        clearAndEnterTextIntoElement(inputPassword, text);
        return this;
    }

    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    //    public void checkInvalidLoginMessageIsVisible() {
//        try {
//            Assert.assertTrue(
//                    "Invalid login message is not visible",
//                    invalidLoginMessage.isDisplayed()
//            );
//        } catch (Exception e) {
//            logger.error("Error while checking invalid login message", e);
//            Assert.fail("Invalid login message is not visible");
//        }
//    }
    @Step
    public void checkInvalidLoginMessageIsVisible() {
        try {
            Assert.assertTrue(
                    "Invalid login message is not visible",
                    invalidLoginMessage.isDisplayed()
            );
            logger.info("Invalid login message is visible");
        } catch (Exception e) {
            logger.error("Error while checking invalid login message", e);
            Assert.fail("Invalid login message is not visible");
        }
    }

    @Step
    public void checkIsSignInButtonVisible() {
        try {
            Assert.assertTrue(
                    "Sign In button should be visible",
                    buttonSignIn.isDisplayed()
            );
            logger.info("Sign In button is visible as expected");
        } catch (Exception e) {
            logger.error("Sign In button is NOT visible", e);
            Assert.fail("Sign In button is NOT visible");
        }
    }

    @Step
    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextIntoElement(inputUserNameRegistrationForm, userName);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailRegistrationForm, email);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordRegistrationForm, password);
        return this;
    }

    @Step
    public LoginPage checkErrorsMessages(String expectedMessages) {
        //error1;error2;error3 -> [error1,error2,error3]
        String[] expectedErrorsArray = expectedMessages.split(SEMICOLON);

        webDriverWait10.withMessage("Error messages")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualMessagesLocator),
                        expectedErrorsArray.length));
        Assert.assertEquals("Number of error messages", expectedErrorsArray.length, listOfActualMessages.size());

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions
                    .assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number" + (i + 1))
                    .isIn(expectedErrorsArray);
        }
        Utils_Custom.waitABit(1);

        softAssertions.assertAll();
        return this;

    }
}