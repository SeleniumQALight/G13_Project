package org.logitTests;

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

    private boolean isButtonSingOutVisible() {
        try{
            return webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
    }catch (Exception e){
        logger.info("Element is not found ");
            return false;
        }


}
}
