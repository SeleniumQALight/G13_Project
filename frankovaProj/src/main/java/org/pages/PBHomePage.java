package org.pages;

import org.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PBHomePage extends ParentPage {
    protected WebDriver webDriver;

    @FindBy(xpath = "//li[contains(@class, 'desctop exchangeRate')]//button/p")
    private WebElement exchangeRateArrow;

    public PBHomePage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    @Override
    public String getRelativeURL() {
        return "/";
    }

    public PBHomePage openPBHomePage() {
        webDriver.get("https://privatbank.ua/");
        return this;

    }

    public PBHomePage getCurrencyRatesTodayUI(String currency) {

        clickOnElement(exchangeRateArrow);

        String locatorBuy = String.format(".//*[@id='%s_buy']", currency);
        String locatorSell = String.format(".//*[@id='%s_sell']", currency);

        // Отримуємо текст елементів - курси валют
        String buyRate = webDriver.findElement(By.xpath(locatorBuy)).getText().trim();
        String sellRate = webDriver.findElement(By.xpath(locatorSell)).getText().trim();

        // Зберігаємо в TestData їх значення
        TestData.buyRateUI = Double.parseDouble(buyRate);
        TestData.saleRateUI = Double.parseDouble(sellRate);

        return this;
    }
}