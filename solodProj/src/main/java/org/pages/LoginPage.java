package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url");
        return this;
    }

    public LoginPage enterTextInputLogin(String text) {
        // WebElement inputLogin = webDriver.findElement(
        //  By.xpath(" .//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys(text);
//        logger.info(text + "qaauto was entered in input UserName");
        clearAndEnterTextIntoElement(inputLogin, text);
        return this;
    }

    public LoginPage enterTextInputPassword(String text) {

//        WebElement inputPassword = webDriver.findElement(
//                By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(text);
//        logger.info(text + " was entered in input Password");
        clearAndEnterTextIntoElement(inputPassword, text);
        return this;
    }

    public void clickOnButtonSingIn() {

        // webDriver.findElement(
//                By.xpath("//button[text()='Sign In']")).click();
//                buttonSignIn.click();
//        logger.info("Button SignIn was clicked");
        clickOnElement(buttonSignIn);
    }


    public HopePage openLoginPageAndFillLoginFromWithValidCrea() {
        openLoginPage()
                .enterTextInputLogin(TestData.VALID_LOGIN_UI)
                .enterTextInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSingIn();
        return new HopePage(webDriver);
    }
}
