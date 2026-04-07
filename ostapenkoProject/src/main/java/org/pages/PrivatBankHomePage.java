package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PrivatBankHomePage {

    private WebDriver driver;

    public PrivatBankHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li//button[@class='btn exchange-rate']")
    private WebElement exchangeRatesButton;

    public void open() {
        driver.get("https://privatbank.ua/");
    }

    public void openExchangeRates() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(exchangeRatesButton)).click();
    }

    public double getSellRate(String currency) {
        String sellRate = "//td[@id='" + currency + "_sell']";
        return Double.parseDouble(driver.findElement(By.xpath(sellRate)).getText());
    }
}