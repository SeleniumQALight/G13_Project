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









