package org.loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTestsAllStepsInOneClass {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");
    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
    }

    @Test
    public void validLogin(){
        openLoginPage();
        fillInAndSubmitLoginForm("qaauto", "123456qwerty");
        Assert.assertTrue("User isn't logged in: Sign Out button is not visible", isButtonSignOutVisible());

    }

    @Test
    public void invalidLogin(){
        openLoginPage();
        fillInAndSubmitLoginForm("invalisusername", "invalidpass12");
        Assert.assertTrue("User is logged in: Invalid Login error message isn't visible and Sign Out button is visible"
                , isInvalidLoginMessageVisible() && !isButtonSignOutVisible());

    }

    private void openLoginPage() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");
    }

    private void fillInAndSubmitLoginForm(String username, String password) {
        WebElement inputLogin = webDriver.findElement(
                By.xpath(".//input[@name='username' and not(@id='username-register')]"));
        inputLogin.clear();
        inputLogin.sendKeys(username);
        logger.info("invalid username was entered in input UserName");

        WebElement inputPassword = webDriver.findElement(
                By.xpath(".//input[@name='password' and not(@id='password-register')]"));
        inputPassword.clear();
        inputPassword.sendKeys(password);
        logger.info("invalid password was entered in input Password");

        webDriver.findElement(By.xpath(".//form[@action='/login']//button")).click();
        logger.info("Button SignIn was clicked");
    }

    private boolean isInvalidLoginMessageVisible() {
        return isEllementVisible(".//div[contains(text(),'Invalid username/password')]");
    }

    private boolean isButtonSignOutVisible() {
        return isEllementVisible(".//form[@action='/logout']//button");
    }

    private boolean isEllementVisible(String locator) {
        try {
            boolean state = webDriver.findElement(By.xpath(locator)).isDisplayed();
            logger.info("Element state: " + state);
            return state;
        }catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }

    }
}
