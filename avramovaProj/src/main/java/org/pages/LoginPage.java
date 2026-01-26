package org.pages;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.data.RegistrationValidationMessages;
import org.junit.Assert;
import org.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utilits.Utils_Custom;

import java.util.List;

import static org.data.RegistrationValidationMessages.SEMICOLON;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement alertMessageAboutInvalidLogin;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistrationForm;

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
        logger.info("login page was opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String text) {
//        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys(text);
//        logger.info(text + " was entered in input UserName");
        clearAndEnterTextIntoElement(inputLogin, text);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String text) {
        clearAndEnterTextIntoElement(inputPassword, text);
        return this;
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isErrorMessageVisible() {
        return isElementDisplayed(alertMessageAboutInvalidLogin);
    }

    public void checkIsButtonSignInVisible() {
        Assert.assertTrue("SignIn button is not visible", isElementDisplayed(buttonSignIn));
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public void checkIsErrorMessageVisible() {
        Assert.assertTrue("Error message is not visible", isErrorMessageVisible());
    }

    public void checkIsInputLoginNotVisible() {
        Assert.assertFalse("Input Login is visible, but should not be", isElementDisplayed(inputLogin));
    }

    public void checkIsInputPasswordNotVisible() {
        Assert.assertFalse("Input Password is visible, but should not be", isElementDisplayed(inputPassword));
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

    public LoginPage checkErrorMessages(String expectedMessages) {
        //error1;error2;error3 -> [error1, error2, error3]
        String[] expectedErrorArray = expectedMessages.split(SEMICOLON);

        webDriverWait10.withMessage("Number of error messages is not as expected")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualMessagesLocator),
                expectedErrorArray.length));

        Utils_Custom.waitABit(1);

        Assert.assertEquals("Number of error messages ", expectedErrorArray.length, listOfActualMessages.size());

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedErrorArray.length; i++) {
            softAssertions
                    .assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number " + (i + 1))
                    .isIn(expectedErrorArray);
        }

        softAssertions.assertAll();

        return this;
    }
}

