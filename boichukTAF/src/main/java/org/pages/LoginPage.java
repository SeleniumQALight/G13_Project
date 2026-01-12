package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[contains(text(),'Invalid username/password.')]")
    private WebElement invalidLoginMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage(){
        webDriver.get(baseUrl);
        logger.info("Login page opened");
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
    public void enterLogin(String login) {
        clearAndEnterTextIntoElement(inputLogin, login);
    }

    public void enterPassword(String password) {
        clearAndEnterTextIntoElement(inputPassword, password);
    public LoginPage enterTextIntoInputPassword (String text){
        clearAndEnterTextIntoElement(inputPassword, text);
        return this;
    }

    public void clickOnSignInButton() {
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

    public void checkIsSignInButtonVisible() {
        Assert.assertTrue("Sign In button is not visible",
                isElementDisplayed(buttonSignIn));
    }

    public void checkInvalidLoginMessageIsVisible() {
        Assert.assertTrue("Invalid login message is not visible",
                isElementDisplayed(invalidLoginMessage));
    }

    public void checkLoginInputsAreNotVisible() {
        Assert.assertFalse("Login input should not be visible",
                isElementDisplayed(inputLogin));
        Assert.assertFalse("Password input should not be visible",
                isElementDisplayed(inputPassword));
    }
}
