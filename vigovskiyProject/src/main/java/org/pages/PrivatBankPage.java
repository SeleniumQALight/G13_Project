package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatBankPage extends CommonActionsWithElements {
    @FindBy(xpath = "//button[contains(@class,'exchange-rate')]")
    WebElement exchangeRatesButton;


    public PrivatBankPage(WebDriver driver) {
        super(driver);
    }

    private String buyRateLocator = "//*[@id='%s_buy']";
    private String sellRateLocator = "//*[@id='%s_sell']";

    private WebElement getBuyElement(String currency) {
        String locator = String.format(buyRateLocator, currency.toUpperCase());
        return webDriver.findElement(By.xpath(locator));
    }

    private WebElement getSellElement(String currency) {
        String locator = String.format(sellRateLocator, currency.toUpperCase());
        return webDriver.findElement(By.xpath(locator));
    }

    public void openPage() {
        webDriver.get("https://privatbank.ua");
    }

    public void clickOnExchangeRates() {
        exchangeRatesButton.click();
    }

    public String getBuyRate(String currency) {
        return getBuyElement(currency).getText().trim();
    }

    public String getSellRate(String currency) {
        return getSellElement(currency).getText().trim();
    }
}