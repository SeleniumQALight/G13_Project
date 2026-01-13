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

public class LoginTestAllStepsInOneClass {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setup(){
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
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputLogin = webDriver.findElement(
                By.xpath("//input[@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        logger.info("qaauto was entered in input UserName");

        WebElement inputPassword = webDriver.findElement(
                By.xpath("//input[@placeholder='Password']"));
        inputPassword.sendKeys("123456qwerty");
        logger.info("123456qwerty password was entered in input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button SignIn was clicked");

//        Assert.assertTrue("User is not LoggedIn: button SignOut is not visible"
//                , isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputLogin = webDriver.findElement(
                By.xpath("//input[@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto123");
        logger.info("qaauto123 was entered in input UserName");

        WebElement inputPassword = webDriver.findElement(
                By.xpath("//input[@placeholder='Password']"));
        inputPassword.sendKeys("123456qwerty123");
        logger.info("123456qwerty123 password was entered in input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button SignIn was clicked");

//        Assert.assertFalse("Sign Out button should not be visible after invalid login", isButtonSignOutVisible());
//        Assert.assertTrue("Sign In button is not visible", isButtonSignInVisible());
//        Assert.assertTrue("Error message is not displayed after invalid login",isErrorMessageVisible());
    }

//    private boolean isButtonSignOutVisible() {
//        try {
//            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            logger.info("Element state " + state);
//            return state;
//        } catch (Exception e){
//            logger.info("Element is not found");
//            return false;
//        }
//    }
    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
            logger.info("Element state " + state);
            return state;
        } catch (Exception e){
            logger.info("Element is not found");
            return false;
        }
    }
    private boolean isErrorMessageVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[text()='Invalid username/password.']")).isDisplayed();
            logger.info("Element state " + state);
            return state;
        } catch (Exception e){
            logger.info("Element is not found");
            return false;
        }
    }
}
