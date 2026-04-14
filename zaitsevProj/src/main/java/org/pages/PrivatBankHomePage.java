package org.pages;

import org.api.dto.responseDto.ExchangeRateDto;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PrivatBankHomePage extends CommonActionsWithElement {
    private Logger logger = Logger.getLogger(getClass());

    private final String privatBankUrl = "https://privatbank.ua/";

    private final By cookieAcceptButton = By.xpath("//button[contains(.,'Погодитися')]");
    private final By ratesButton = By.xpath("//button[@type='button' and contains(@class,'exchange-rate')]");

    public PrivatBankHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public PrivatBankHomePage openPrivatBankHomePage() {
        webDriver.get(privatBankUrl);
        logger.info("PrivatBank home page was opened: " + privatBankUrl);

        webDriverWait15.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        return this;
    }

    public void acceptCookiesIfPresent() {
        List<WebElement> buttons = webDriver.findElements(cookieAcceptButton);
        if (!buttons.isEmpty() && buttons.get(0).isDisplayed()) {
            buttons.get(0).click();
            logger.info("Cookie banner was accepted");

            webDriverWait15.until(driver ->
                    driver.findElements(cookieAcceptButton).isEmpty()
                            || !driver.findElement(cookieAcceptButton).isDisplayed()
            );
        }
    }

    public void openRatesModal() {
        acceptCookiesIfPresent();

        webDriverWait15.until(driver ->
                !driver.findElements(ratesButton).isEmpty()
                        && driver.findElement(ratesButton).isDisplayed()
                        && driver.findElement(ratesButton).isEnabled()
        );

        webDriver.findElement(ratesButton).click();
        logger.info("Rates button was clicked");

        webDriverWait15.until(driver ->
                !driver.findElements(By.xpath("//*[contains(text(),'EUR') and contains(text(),'49')]")).isEmpty()
                        || !driver.findElements(By.xpath("//*[contains(text(),'USD') and contains(text(),'43')]")).isEmpty()
        );

        logger.info("Rates dropdown is visible");
    }

    public ExchangeRateDto getCashRateForCurrency(String currency) {
        openRatesModal();

        By exactCurrencyRow = By.xpath(
                "//*[contains(normalize-space(.), '" + currency.toUpperCase() + " UAH')]"
        );

        webDriverWait15.until(driver ->
                !driver.findElements(exactCurrencyRow).isEmpty()
        );

        List<WebElement> rows = webDriver.findElements(exactCurrencyRow);

        WebElement neededRow = null;
        for (WebElement row : rows) {
            String rowText = row.getText().trim().replace(",", ".").replace("\n", " ");
            if (rowText.matches(".*\\b" + currency.toUpperCase() + "\\s+UAH\\s+\\d+(\\.\\d+)?\\s+\\d+(\\.\\d+)?.*")) {
                neededRow = row;
                logger.info("Matched row for " + currency + ": " + rowText);
                break;
            }
        }

        Assert.assertNotNull("Rate row for currency " + currency + " was not found", neededRow);

        String rowText = neededRow.getText().trim().replace(",", ".").replace("\n", " ");
        String[] parts = rowText.split("\\s+");

        Double buyRate = null;
        Double saleRate = null;

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equalsIgnoreCase(currency) && i + 3 < parts.length && parts[i + 1].equalsIgnoreCase("UAH")) {
                buyRate = Double.valueOf(parts[i + 2]);
                saleRate = Double.valueOf(parts[i + 3]);
                break;
            }
        }

        Assert.assertNotNull("Buy rate was not found for currency " + currency, buyRate);
        Assert.assertNotNull("Sale rate was not found for currency " + currency, saleRate);

        ExchangeRateDto rateDto = new ExchangeRateDto();
        rateDto.setCurrency(currency.toUpperCase());
        rateDto.setBaseCurrency("UAH");
        rateDto.setPurchaseRate(buyRate);
        rateDto.setSaleRate(saleRate);

        logger.info("UI rate for currency " + currency + ": " + rateDto);

        return rateDto;
    }
}