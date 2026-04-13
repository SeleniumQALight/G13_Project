package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class PrivatBankPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // locators
    private By exchangeRateButton = By.xpath("//button[contains(@class,'exchange-rate')]");
    private By usdBuy = By.id("USD_buy");
    private By usdSale = By.id("USD_sale");
    private By eurBuy = By.id("EUR_buy");
    private By eurSale = By.id("EUR_sale");

    public PrivatBankPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // 1. Open page
    public void openPage() {
        driver.get("https://privatbank.ua/");
    }

    // 2. Open exchange rates block
    public void openExchangeRates() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'exchange-rate')]")
        ));
        button.click();

        // чекаємо появу хоча б одного курсу
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("USD_buy")));
    }

    // 3. Generic method to get buy rate
    public String getBuyRate(String currency) {
        By locator = By.id(currency + "_buy");

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        // ⛔ чекаємо поки текст стане НЕ пустим
        wait.until(driver -> {
            String text = element.getText().trim();
            return !text.isEmpty();
        });

        String rate = element.getText().trim();

        if (rate.isEmpty()) {
            throw new RuntimeException("Buy rate is empty for " + currency);
        }

        return rate;
    }

    // 4. Generic method to get sale rate
    public String getSaleRate(String currency) {
        By locator = By.id(currency + "_sale");

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        wait.until(driver -> !element.getText().trim().isEmpty());

        return element.getText().trim();
    }

    // Optional: convenience methods
    public String getUsdBuy() {
        return getBuyRate("USD");
    }

    public String getUsdSale() {
        return getSaleRate("USD");
    }

    public String getEurBuy() {
        return getBuyRate("EUR");
    }

    public String getEurSale() {
        return getSaleRate("EUR");
    }
}
