package org.loginTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.pages.ParentPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.pages.ParentPage.baseUrl;

public class LoginTestWithSelenide {
@Test
    public void validLogin(){
    //Should be on LoginPage
    SelenideElement inputUserName = $(By.xpath(".//input[@placeholder='Username']"));
    SelenideElement inputPassword = $(By.xpath(".//input[@placeholder='Password']"));
    SelenideElement buttonSignIn = $(By.xpath(".//button[text()='Sign In']"));
    //Should be on HomePage
    SelenideElement buttonMyProfile = $(By.xpath("//img[@alt='My profile']"));

    WebDriverManager.chromedriver().setup();

    open(baseUrl);

    inputUserName.setValue("qaauto");
    inputPassword.setValue("123456qwerty");
    buttonSignIn.click();

    buttonMyProfile.shouldBe(Condition.visible);
}
}
