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

public class LoginTestAllStepsinOneClass {

    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());


    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
    }

    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputLogin = webDriver.findElement(
                By.xpath("//input[@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        logger.info("qaauto was entered in input UserName");

        WebElement inputPassword = webDriver.findElement(
                By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("123456qwenty password was enetered in input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button SignIn was clicked");

        Assert.assertTrue("User is not Logged in: button SignOut is not visible",
                isButtonSignOutVisible());

    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(
                    By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Sign Out Element state: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Sign Out Element is not found");
            return false;
        }
    }

    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputLogin = webDriver.findElement(
                By.xpath("//input[@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("invalid_login");
        logger.info("invalid_login was entered in input UserName");

        WebElement inputPassword = webDriver.findElement(
                By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("invalid_password");
        logger.info("invalid_password was enetered in input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Sign In button was clicked");

        Assert.assertFalse("User is not Logged in: button SignOut is not visible",
                isButtonSignOutVisible());

        Assert.assertTrue("User is Logged in: button Sign In is not visible",
                isButtonSignInVisible());

        Assert.assertTrue("User is Logged in: error message about invalid login/password is not visible",
                isErrorMessageInvalidCredVisible());
    }


    private boolean isButtonSignInVisible() {
       try {
           boolean state = webDriver.findElement(
                   By.xpath("//button[text()='Sign In']")).isDisplayed();
           logger.info("Sign In Element state: " + state);
           return state;
       } catch (Exception e) {
          logger.info("Sign In Element is not found");
           return false;
       }
    }


    private boolean isErrorMessageInvalidCredVisible() {
        try {
            boolean state = webDriver.findElement(
                    By.xpath("//div[text()='Invalid username/password.']")).isDisplayed();
            logger.info("ErrorMessage Element state: " + state);
            return state;
        } catch (Exception e) {
            logger.info("ErrorMessage Element is not found");
            return false;
        }
    }
}
