package org.api.dto.responseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ExchangeRatesResponseDto {
    @JsonProperty("date")
    private String date;

    @JsonProperty("bank")
    private String bank;

    @JsonProperty("baseCurrency")
    private int baseCurrency;

    @JsonProperty("baseCurrencyLit")
    private String baseCurrencyLit;

    @JsonProperty("exchangeRate")
    private List<ExchangeRateDto> exchangeRate;

    public ExchangeRatesResponseDto() {}

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getBank() { return bank; }
    public void setBank(String bank) { this.bank = bank; }

    public int getBaseCurrency() { return baseCurrency; }
    public void setBaseCurrency(int baseCurrency) { this.baseCurrency = baseCurrency; }

    public String getBaseCurrencyLit() { return baseCurrencyLit; }
    public void setBaseCurrencyLit(String baseCurrencyLit) { this.baseCurrencyLit = baseCurrencyLit; }

    public List<ExchangeRateDto> getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(List<ExchangeRateDto> exchangeRate) { this.exchangeRate = exchangeRate; }

    @Override
    public String toString() {
        return "ExchangeRatesResponseDto{" +
                "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrency=" + baseCurrency +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
