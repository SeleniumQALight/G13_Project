package org.pages;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.data.RegistrationValidationMessages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utils.Utils_Custom;

import java.util.List;

import static org.data.RegistrationValidationMessages.SEMICOLON;
import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']" )
    private WebElement inputLogin;

    @FindBy(xpath ="//input[@placeholder='Password']" )
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']" )
    private WebElement buttonSignIn;

    @FindBy(id="username-register")
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id="email-register")
    private WebElement inputEmailRegistrationForm;

    @FindBy(id="password-register")
    private WebElement inputPasswordRegistrationForm;

    final static String listOfActualMessagesXpath
        = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listOfActualMessagesXpath)
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
        logger.info("Login page was opened with url: " + baseUrl);
        return  this;
    }
    public LoginPage enterTextIntoInputLogin (String text) {
//        WebElement inputLogin = webDriver.findElement(
//                By.xpath("//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys(text);
//        logger.info(text + " was entered in input UserName");
        clearAndEnterTextIntoElement(inputLogin, text);
        return this;
    }

    public LoginPage enterTextIntoInputPassword (String text) {
        clearAndEnterTextIntoElement(inputPassword, text);
        return this;
    }

    public void clickOnButtonSignIn () {
        clickOnElement(buttonSignIn);

//      webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//      buttonSignIn.click();
//      logger.info("Button 'Sign In' was clicked");
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        this.openLoginPage();
        enterTextIntoInputLogin(VALID_LOGIN_UI);
        enterTextIntoInputPassword(VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return  new HomePage(webDriver);
    }
    public void checkIsButtonSingInVisible() {
        Assert.assertTrue("Button SignIn is NOT visible", isButtonSingInVisible());
        logger.info("Button SignIn is visible");
    }

    public boolean isButtonSingInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(
                    "//button[text()='Sign In']")).isDisplayed();
            logger.info("SignIn button is found:" + state);
            return state;
        } catch (Exception e) {
            logger.info("SignIn button is not found");
            return false;
        }
    }

    public void checkIsTextErrorInvalidDataVisible() {
        Assert.assertTrue("Text error 'Invalid username/password' is NOT visible",
                isTextErrorInvalidDataVisible());
        logger.info("Text error 'Invalid username/password' is visible");
    }

    private boolean isTextErrorInvalidDataVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[text()='Invalid username/password.']")).isDisplayed();
            logger.info("Text error 'Invalid username/password' is found: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Text error 'Invalid username/password' is not found");
            return false;
        }
    }

    public void checkIsInputLoginNotVisible() {
        Assert.assertFalse("Input Login is visible", isInputLoginIsVisible());
        logger.info("Input Login is NOT visible");
    }

    private boolean isInputLoginIsVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(
                    "//input[@placeholder='Username']")).isDisplayed();
            logger.info("Input Login is found:" + state);
            return state;
        } catch (Exception e) {
            logger.info("Input Login is not found");
            return false;
        }
    }

    public void checkIsInputPasswordNotVisible() {
        Assert.assertFalse("Input Password is visible", isInputPasswordIsVisible());
        logger.info("Input Password is NOT visible");
    }

    private boolean isInputPasswordIsVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(
                    "//input[@placeholder='Password']")).isDisplayed();
            logger.info("Input Password is found:" + state);
            return state;
        } catch (Exception e) {
            logger.info("Input Password is not found");
            return false;
        }
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
        //error1;error2;error3->[error1,error2,error3]
        String[] expectedErrorsArray = expectedMessages.split(SEMICOLON);

        webDriverWait10.withMessage("Number of error messages is incorrect")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualMessagesXpath),
                expectedErrorsArray.length));

        Utils_Custom.waitABit(1);

        Assert.assertEquals("Number of errors messages ", expectedErrorsArray.length, listOfActualMessages.size());
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number " + (i+1))
                    .isIn(expectedErrorsArray);
        }
        softAssertions.assertAll();
        return this;
    }
}
