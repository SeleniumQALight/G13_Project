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

    private WebDriver webDriver;

    @FindBy(xpath = "//button[contains(@class,'exchange-rate')]")
    private WebElement exchangeRatesButton;

    public PrivatBankHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // 🔥 ОБОВ'ЯЗКОВО
    }

    public void openPage() {
        webDriver.get("https://privatbank.ua/");
    }

    public void openExchangeRates() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(exchangeRatesButton)).click();
    }

    public double getBuyRate(String currency) {
        return Double.parseDouble(
                webDriver.findElement(By.xpath("//td[@id='" + currency + "_buy']"))
                        .getText()
                        .replace(",", ".")
        );
    }

    public double getSaleRate(String currency) {
        return Double.parseDouble(
                webDriver.findElement(By.xpath("//td[@id='" + currency + "_sell']"))
                        .getText()
                        .replace(",", ".")
        );
    }
}
