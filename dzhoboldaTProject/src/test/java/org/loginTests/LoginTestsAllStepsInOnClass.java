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

public class LoginTestsAllStepsInOnClass {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void  setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");

    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Brousers was closed");

    }



    @Test
    public void validLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");
        WebElement inputLogin = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("Dzh2399");
        logger.info("nikname was entered in input Username");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("Stringi23992399");
        logger.info("password Stringi23992399 was entered in input Username");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button SignIn  was clicked");

        Assert.assertTrue("User is not Logged In ",isButtonSingOutVisible());

    }

    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputLogin = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("wrongUser");
        logger.info("Invalid username entered");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("Stringi23992399");
        logger.info("Invalid password entered");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Sign In button clicked");

        // Проверки
        Assert.assertFalse("Sign Out button should not be visible", isButtonSingOutVisible());
        Assert.assertTrue("Sign In button should be visible", isButtonSignInVisible());
        Assert.assertTrue("Error message is not displayed", isErrorMessageVisible());
    }

    private boolean isButtonSingOutVisible() {
        try{
            return webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
    }catch (Exception e){
        logger.info("Element is not found ");
            return false;
        }

}

//    private boolean isButtonSignOutVisible() {
//        try {
//            return webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//        } catch (Exception e) {
//            logger.info("Sign Out button is not found");
//            return false;
//        }
//    }
    private boolean isButtonSignInVisible() {
        try {
            return webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
        } catch (Exception e) {
            logger.info("Sign In button is not found");
            return false;
        }
    }

    private boolean isErrorMessageVisible() {
        try {
            return webDriver.findElement(By.xpath("//div[text()='Invalid username/password.']")).isDisplayed();
        } catch (Exception e) {
            logger.info("Error message is not found");
            return false;
        }

    }

}
