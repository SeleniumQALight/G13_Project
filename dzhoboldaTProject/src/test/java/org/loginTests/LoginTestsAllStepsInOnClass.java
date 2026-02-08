package org.loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.data.TestData.ValidPassword;

public class LoginTestsAllStepsInOnClass extends BaseTest {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

//    @Test
//    public void validLogin(){
//        webDriver.get("https://aqa-complexapp.onrender.com");
//        logger.info("Site was opened");
//        WebElement inputLogin = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys("Dzh2399");
//        logger.info("nikname was entered in input Username");
//
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys("Stringi23992399");
//        logger.info("password Stringi23992399 was entered in input Username");
//
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        logger.info("Button SignIn  was clicked");
//
//        Assert.assertTrue("User is not Logged In ",isButtonSingOutVisible());
//
//    }

//    @Test
//    public void invalidLogin() {
//        webDriver.get("https://aqa-complexapp.onrender.com");
//        logger.info("Site was opened");
//
//        WebElement inputLogin = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputLogin.clear();
//        inputLogin.sendKeys("wrongUser");
//        logger.info("Invalid username entered");
//
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys("Stringi23992399");
//        logger.info("Invalid password entered");
//
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        logger.info("Sign In button clicked");
//
//        // Проверки
//        Assert.assertFalse("Sign Out button should not be visible", isButtonSingOutVisible());
//        Assert.assertTrue("Sign In button should be visible", isButtonSignInVisible());
//        Assert.assertTrue("Error message is not displayed", isErrorMessageVisible());
//    }
//Старые тесты закомментированы, потому что один из них из HW 2 и ниже приведен новый тест с улучшенной читаемостью
// Первый тест переделан согласно POM и содеится в другом класе

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin("wrongUser")
                .enterTextIntoInputPassword(ValidPassword)
                .clickOnButtonSignIn();

        //  Sign Out нет
        Assert.assertFalse("Sign Out button should not be visible",
                pageProvider.getHomePage()
                        .isElementDisplayed(
                                pageProvider.getHomePage().getButtonSignOut()
                        ));

        //  Sign In есть
        Assert.assertTrue("Sign In button should be visible",
                pageProvider.getLoginPage()
                        .isElementDisplayed(
                                pageProvider.getLoginPage().getButtonSignIn()
                        ));

        //  сообщение об ошибке
        Assert.assertTrue("Error message should be visible",
                pageProvider.getLoginPage()
                        .isElementDisplayed(
                                pageProvider.getLoginPage().getErrorMessage()
                        ));
    }


}




    private boolean isButtonSignInVisible() {
        try {
            return webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
        } catch (Exception e) {
            logger.info("Sign In button is not found");
            return false;
        }
    }



