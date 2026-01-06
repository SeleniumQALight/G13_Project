package org.pages;

import org.apache.log4j.Logger;
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

    public void openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url");
    }

    public void enterTextInputLogin(String text) {
       // WebElement inputLogin = webDriver.findElement(
              //  By.xpath(" .//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys(text);
//        logger.info(text + "qaauto was entered in input UserName");
        clearAndEnterTextIntoElement(inputLogin,text);
    }

    public void enterTextInputPassword(String text) {

//        WebElement inputPassword = webDriver.findElement(
//                By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(text);
//        logger.info(text + " was entered in input Password");
        clearAndEnterTextIntoElement(inputPassword,text);
    }

    public void clickOnButtonSingIn() {

       // webDriver.findElement(
//                By.xpath("//button[text()='Sign In']")).click();
//                buttonSignIn.click();
//        logger.info("Button SignIn was clicked");
        clickOnElement(buttonSignIn);
    }


}
