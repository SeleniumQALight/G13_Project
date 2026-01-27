package org.pages;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
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

  @FindBy(xpath = "//div[text() = 'Invalid username/password.']")
  private WebElement invalidUsernamePasswordMessage;

  @FindBy(id = "username-register")
  private WebElement inputUserNameRegistrationForm;

  @FindBy(id = "email-register")
  private WebElement inputEmailRegistrationForm;

  @FindBy(id = "password-register")
  private WebElement inputPasswordRegistrationForm;

    final static String listOfActualMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listOfActualMessagesLocator)
    private List<WebElement> listOfActualMessages;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public void checkIsErrorMessageVisibleWithText(){
        Assert.assertTrue("Message \"Invalid username/password.\" is not visible",
                isElementDisplayed(invalidUsernamePasswordMessage));
        logger.info("Message \"Invalid username/password.\" is visible");
    }

    public void checkIsErrorMessageIsVisibleWithText (String expectedText){
        Assert.assertTrue("Error message is not visible", isElementDisplayed(invalidUsernamePasswordMessage));
        logger.info("Error message is visible: " + expectedText);
    }

    public void checkIsLoginInputNotVisible(){
        Assert.assertFalse("Login input is visible, but should not be", isElementDisplayed(inputLogin));
        logger.info("Login input is not visible");
    }

    public void checkIsPasswordInputNotVisible(){
        Assert.assertFalse("Password input is visible, but should not be", isElementDisplayed(inputPassword));
        logger.info("Password input is not visible");
    }

    public LoginPage openLoginPage(){
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin (String text){
//    WebElement inputLogin = webDriver.findElement(
//            By.xpath("//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys(text);
//        logger.info(text + " was entered in input Username");
        clearAndEnterTextIntoElement(inputLogin, text);
        return this;
    }

    public LoginPage enterTextIntoInputPassword (String text){
        clearAndEnterTextIntoElement(inputPassword, text);
        return this;
    }

    public void clickOnButtonSignIn(){
      clickOnElement(buttonSignIn);
    }


    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage checkIsRedirectToLoginPage() {
        // TODO Check URL
        // TODO check some unique element on LoginPage
        return this;
    }

    public LoginPage checkIsLoginInputVisible(){
        Assert.assertTrue("Login input is not visible", isElementDisplayed(inputLogin));
        logger.info("Login input is not visible");
        return this;
    }

    public LoginPage checkIsPasswordInputVisible(){
        Assert.assertTrue("Password input is not visible", isElementDisplayed(inputPassword));
        logger.info("Password input is not visible");
        return this;
    }

    public LoginPage checkIsButtonSignInVisible(){
        Assert.assertTrue("Button Sign In is not visible", isElementDisplayed(buttonSignIn));
        logger.info("Button SignIn is visible");
        return this;
    }

    public LoginPage enterTextintoRegistrationUserField(String userName) {
        clearAndEnterTextIntoElement(inputUserNameRegistrationForm, userName);
        return this;
    }

    public LoginPage enterTextintoRegistrationEmailField(String email) {
        clearAndEnterTextIntoElement(inputEmailRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextintoRegistrationPasswordField(String password) {
        clearAndEnterTextIntoElement(inputPasswordRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedMessages) {
        // error1;error2;error3 -> [error1, error2, error3]
        String[] expectedErrorsArray = expectedMessages.split(SEMICOLON);

        webDriverWait10.withMessage("Number of error messages is not as expected")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualMessagesLocator)
                , expectedErrorsArray.length));

        Utils_Custom.waitABit(1);
        Assert.assertEquals("Number of error messages ", expectedErrorsArray.length, listOfActualMessages.size());

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(listOfActualMessages.get(i).getText())
                    .as("Error message at index " + (i + 1))
                    .isIn(expectedErrorsArray);

        }

        softAssertions.assertAll();

        return this;
    }
}
