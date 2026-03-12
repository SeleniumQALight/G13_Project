package org.api.dto.responseDto;
import org.api.ExchangeRateDTO;

import java.util.List;

public class ExchangeRatesResponseDTO {

    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;

    private List<ExchangeRateDTO> exchangeRate;

    public ExchangeRatesResponseDTO() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(int baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

    public List<ExchangeRateDTO> getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(List<ExchangeRateDTO> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
