package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

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

     public LoginPage(WebDriver webDriver) {
        super(webDriver);
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
        //TODO Check URL
        //TODO check some unique element
        return this;
    }


}
