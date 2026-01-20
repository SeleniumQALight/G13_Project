package org.loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTestAllStepsInOneClass {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());


    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser initialized");
    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser Closed");
    }

    @Test
    public void validLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");
        WebElement inputLogin = webDriver.findElement(
                By.xpath("//input[@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        logger.info("Login 'qaauto' was entered in input field");

        WebElement inputPassword = webDriver.findElement(
                By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("Pass '123456qwerty' was entered in input field");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button 'Sign In' was clicked");

        Assert.assertTrue("User is not loggedIn: button SignOut is not visible",
                isButtonSingOutVisible());

    }

    @Test
    public void invalidLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");
        WebElement inputLogin = webDriver.findElement(
                By.xpath("//input[@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto123");
        logger.info("Login 'qaauto123' was entered in input field");

        WebElement inputPassword = webDriver.findElement(
                By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("Pass '123456qwerty' was entered in input field");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button 'Sign In' was clicked");

        Assert.assertFalse("User is LoggedIn: button SignOut is visible",isButtonSingOutVisible());
        Assert.assertTrue("User is loggedIn: button SignIn is NOT visible",
                isButtonSingInVisible());
        Assert.assertTrue("User is loggedIn: Text error 'Invalid username/password' is NOT visible", isTextErrorInvalidDataVisible());




    }


    private boolean isButtonSingOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(
                    "//button[text()='Sign Out']")).isDisplayed();
            logger.info("Sign Out button is found:" + state);
            return state;
        }catch (Exception e){
            logger.info("Sign Out button is not found");
            return false;
        }
    }

    private boolean isButtonSingInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(
                    "//button[text()='Sign In']")).isDisplayed();
            logger.info("Sign In button is found:" + state);
            return state;
        }catch (Exception e){
            logger.info("Sign In button is not found");
            return false;
        }
    }

    private boolean isTextErrorInvalidDataVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[text()='Invalid username/password.']")).isDisplayed();
            logger.info("Text error 'Invalid username/password' is found: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Text error 'Invalid username/password' is not found");
            return false;
        }
    }
}
