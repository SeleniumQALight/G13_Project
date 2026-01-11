package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testdata.TestData.VALID_LOGIN_UI;
import static org.testdata.TestData.VALID_PASSWORD_UI;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath="//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath="//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath="//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement errorMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
        return this;
    }

    public LoginPage enterTextIntoInputLogin(String text) {
//        WebElement inputLogin = webDriver.findElement(
//                By.xpath("//input[@placeholder='Username']"));
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
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        buttonSignIn.click();
//        logger.info("Button Sign In was clicked");
        clickOnElement(buttonSignIn);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(VALID_LOGIN_UI);
        enterTextIntoInputPassword(VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);

    }

    public LoginPage checkIsErrorMessageVisible() {
        Assert.assertTrue("Error message is not visible", isErrorMessageVisible());
        logger.info("Error message is visible");
        return this;
    }

    private boolean isErrorMessageVisible() {
        try {
            boolean state = errorMessage.isDisplayed();
            logger.info("Element state: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    public LoginPage checkIsErrorMessageText(String text) {
        Assert.assertEquals("Text in error message is not expected", text,
                errorMessage.getText());
        return this;
    }

    public LoginPage checkIsButtonSignOutVisible(){
        Assert.assertFalse("Button Sign Out is not visible", isButtonSignOutVisible());
        logger.info("Button Sign Out is not visible");
        return this;
    }

    public boolean isButtonSignOutVisible() {
        try{
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element state: " + state);
            return state;
        } catch (Exception e){
            logger.info("Element is not found");
            return false;
        }
    }

    public LoginPage checkIsEnterTextIntoInputLoginVisible() {
        Assert.assertTrue("Input Login is not visible", isEnterTextIntoInputLoginVisible());
        logger.info("Input Login is visible");
        return this;
    }

    public LoginPage checkIsEnterTextIntoInputPasswordVisible() {
        Assert.assertTrue("Input Password is not visible", isEnterTextIntoInputPasswordVisible());
        logger.info("Input Password is visible");
        return this;
    }

    private boolean isEnterTextIntoInputLoginVisible() {
        try {
            return inputLogin.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isEnterTextIntoInputPasswordVisible() {
        try {
            return inputPassword.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
