package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.junit.Assert;
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
    private WebElement messageInvalidUserOrPassword;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with ull " + baseUrl);
        return this;
    }

    public LoginPage enterTestIntoInputLogin(String text) {
        //  WebElement inputLogin = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys(text);
//        logger.info(text + " was entered in input UserName");
        clearAndEnterTextIntoElement(inputLogin, text);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String text) {
        // WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(text);
//        logger.info(text + " password was entered in input Password");
        clearAndEnterTextIntoElement(inputPassword, text);
        return this;
    }

    public LoginPage clickOnButtonSignIn() {
        // webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        buttonSignIn.click();
//        logger.info("Button SignIn was clicked");
        clickOnElement(buttonSignIn);
        return this;
    }

    public LoginPage checkButtonSignInIsVisible() {
        Assert.assertTrue("Button Sign In is not visible", isWebElementIsVisible(buttonSignIn));
        logger.info("Button Sign In is visible");
        return this;
    }


    public void checkMessageInvalidUserOrPassword() {
        Assert.assertTrue("Message \"Invalid User Or Password\" is not visible", isWebElementIsVisible(messageInvalidUserOrPassword));
        logger.info("Message \"Invalid User Or Password\" is visible");
    }


    public HomePage openLoginPageAndFillInLoginFormWithValidCred() {
        this.openLoginPage();
        enterTestIntoInputLogin(TestData.VALID_LOGIN);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}
