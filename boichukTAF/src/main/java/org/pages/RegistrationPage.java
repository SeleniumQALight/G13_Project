package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By usernameInput = By.xpath("//input[@name='username']");
    private By emailInput = By.xpath("//input[@name='email']");
    private By passwordInput = By.xpath("//input[@name='password']");
    private By signUpButton = By.xpath("//button[@type='submit']");
    private By fieldErrors = By.xpath("//div[contains(@class,'liveValidateMessage')]");
    private By globalError = By.xpath("//div[contains(@class,'alert-danger')]");

    public void open() {
        driver.get("https://aqa-complexapp.onrender.com/");
    }

    public void typeUsername(String username) {
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void typeEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void typePassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickSignUp() {
        driver.findElement(signUpButton).click();
    }

    public List<String> getFieldErrors() {
        List<String> errors = new ArrayList<>();

        driver.findElements(fieldErrors).forEach(e -> {
            errors.add(e.getText().trim());
        });

        return errors;
    }

    public String getGlobalError() {
        if (driver.findElements(globalError).isEmpty()) {
            return "";
        }
        return driver.findElement(globalError).getText().trim();
    }
}