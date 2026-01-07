package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login Page was Opened with url " + baseUrl);
    }

    public void enterTextIntoInputLogin(String text) {
       /* WebElement inputLogin = webDriver.findElement(
                By.xpath("//input[@placeholder='Username']"));*/
       /* inputLogin.clear();
        inputLogin.sendKeys(text);
        logger.info(text + " was entered in input UserName");*/
    clearAndEnterTextIntoElement(inputLogin, text);
    }

    public void enterTextIntoInputPassword(String text) {
    clearAndEnterTextIntoElement(inputPassword, text);
    }

    public void clickOnButtonSignIn() {
        //webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        /*buttonSignIn.click();
        logger.info("Button SignIn was clicked");*/
        clickOnElement(buttonSignIn);
    }

    public void checkIsButtonSignInVisible(){
        checkIsElementVisible(buttonSignIn);
    }

    public void checkIsErrorMessageInvalidCredVisible(){
        checkIsElementVisible(errorMessageInvalidCred);
    }

    public void checkIsNotInputLoginVisible(){
        checkIsNotElementVisible(inputLogin);
    }


    public void checkIsNotInputPasswordVisible(){
        checkIsNotElementVisible(inputPassword);
    }


}
