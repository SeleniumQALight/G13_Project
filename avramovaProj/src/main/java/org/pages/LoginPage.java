package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("login page was opened with url " + baseUrl);
    }

    public LoginPage enterTextIntoInputLogin(String text) {
//        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys(text);
//        logger.info(text + " was entered in input UserName");
        clearAndEnterTextIntoElement(inputLogin,text);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String text) {
        clearAndEnterTextIntoElement(inputPassword, text);
        return this;
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isErrorMessageVisible(){
        return isElementDisplayed(alertMessageAboutInvalidLogin);
    }

    public void checkIsButtonSignInVisible(){
        Assert.assertTrue("SignIn button is not visible", isElementDisplayed(buttonSignIn));
    }
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
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
}

