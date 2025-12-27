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

public class LoginTestAllOneClass {
    private WebDriver webDriver; //интерфейс описывает любой браузер
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();//идет в инет и скачивает атуальую версию браузера, стягиваем исп файл
        webDriver = new ChromeDriver();//здесь инициализируем конкретный вебдрайвер для хром
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");

    }

    @After
    public void tearDown() {
        webDriver.quit();//метод квит закрывает брацзер и уничтожает инф из памяти, клоз просто визуально заур вкладку
        logger.info("Browser was closed");
    }

    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");
        WebElement inputLogin = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputLogin.clear();
        inputLogin.sendKeys("qaauto");
        logger.info("qaauto was entered in input UserName");
        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("123456qwert password was entered");
        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button SignIn was clicked");
        Assert.assertTrue("User is not logged in: button sighOut is not visible",
                isButtonSighOutIsVisible());


    }

    private boolean isButtonSighOutIsVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element state: "+state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }

    }
}
