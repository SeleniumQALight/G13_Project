package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatBankPage extends CommonActionsWithElements {

    public PrivatBankPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(@class,'exchange-rate')]")
    private WebElement exchangeRatesButton;

    @FindBy(id = "USD_buy")
    private WebElement usdBuy;

    @FindBy(id = "USD_sell")
    private WebElement usdSell;

    @FindBy(id = "EUR_buy")
    private WebElement eurBuy;

    @FindBy(id = "EUR_sell")
    private WebElement eurSell;

    @FindBy(id = "PLN_buy")
    private WebElement plnBuy;

    @FindBy(id = "PLN_sell")
    private WebElement plnSell;

    public void openPage() {
        webDriver.get("https://privatbank.ua");
    }

    public void clickOnExchangeRates() {
        clickOnElement(exchangeRatesButton);
    }


    public String getUsdBuy() {
        return usdBuy.getText().trim();
    }

    public String getUsdSale() {
        return usdSell.getText().trim();
    }

    public String getEurBuy() {
        return eurBuy.getText().trim();
    }

    public String getEurSale() {
        return eurSell.getText().trim();
    }

    public String getPlnBuy() {
        return plnBuy.getText().trim();
    }

    public String getPlnSale() {
        return plnSell.getText().trim();
    }
}