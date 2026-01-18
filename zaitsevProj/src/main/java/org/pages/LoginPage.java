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

  @FindBy(xpath = "//div[text() = 'Invalid username/password.']")
  private WebElement invalidUsernamePasswordMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage(){
    public void checkIsButtonSignInVisible(){
        Assert.assertTrue("Button Sign In is not visible", isElementDisplayed(buttonSignIn));
        logger.info("Button SignIn is visible");
    }

    public void checkIsErrorMessageVisibleWithText(){
        Assert.assertTrue("Message \"Invalid username/password.\" is not visible",
                isElementDisplayed(invalidUsernamePasswordMessage));
        logger.info("Message \"Invalid username/password.\" is visible");
    }

    public void checkIsErrorMessageIsVisibleWithText (String expectedText){
        Assert.assertTrue("Error message is not visible", isElementDisplayed(invalidUsernamePasswordMessage));
        logger.info("Error message is visible");
        Assert.assertEquals("Error message is not correct", expectedText,
                invalidUsernamePasswordMessage.getText());
        logger.info("Error text is correct");
    }

    public void checkIsLoginInputNotVisible(){
        Assert.assertFalse("Login input is visible, but should not be", isElementDisplayed(inputLogin));
        logger.info("Login input is not visible");
    }

    public void checkIsPasswordInputNotVisible(){
        Assert.assertFalse("Password input is visible, but should not be", isElementDisplayed(inputPassword));
        logger.info("Password input is not visible");
    }

    public void openLoginPage(){
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
}
