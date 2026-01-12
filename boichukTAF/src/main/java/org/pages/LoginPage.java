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

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement invalidLoginMessage;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
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

//    public void checkInvalidLoginMessageIsVisible() {
//        try {
//            Assert.assertTrue(
//                    "Invalid login message is not visible",
//                    invalidLoginMessage.isDisplayed()
//            );
//        } catch (Exception e) {
//            logger.error("Error while checking invalid login message", e);
//            Assert.fail("Invalid login message is not visible");
//        }
//    }

    public void checkInvalidLoginMessageIsVisible() {
        try {
            Assert.assertTrue(
                    "Invalid login message is not visible",
                    invalidLoginMessage.isDisplayed()
            );
            logger.info("Invalid login message is visible");
        } catch (Exception e) {
            logger.error("Error while checking invalid login message", e);
            Assert.fail("Invalid login message is not visible");
        }
    }


    public void checkIsSignInButtonVisible() {
        try {
            Assert.assertTrue(
                    "Sign In button should be visible",
                    buttonSignIn.isDisplayed()
            );
            logger.info("Sign In button is visible as expected");
        } catch (Exception e) {
            logger.error("Sign In button is NOT visible", e);
            Assert.fail("Sign In button is NOT visible");
        }
    }

}