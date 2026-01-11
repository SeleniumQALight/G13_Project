package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
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
    private WebElement buttonSingIn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened by url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String text) {
//        WebElement inputLogin = webDriver.findElement(
//                By.xpath("//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys(text);
//        logger.info(text + " was entered in input Username");
        clearAndEnterTextIntoElement(inputLogin, text);
        return this;
    }

    public LoginPage enterTextIntoInputPasswort(String text) {
        clearAndEnterTextIntoElement(inputPassword, text);
        return this;
    }

    public void clickOnButtonSignIn(){
        clickOnElement(buttonSingIn);
    }

    public HomePage openLoginPageAndFillFormWithValidCred() {
        this.openLoginPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextIntoInputPasswort(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}