package org.api.dto;

import java.util.List;

public class PrivatExchangeDto {
    String date;
    String bank;
    Integer baseCurrency;
    String baseCurrencyLit;
    List<ExchangeRateDto> exchangeRate;

    public void setDate(String date) {
        this.date = date;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

    public void setBaseCurrency(Integer baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setExchangeRate(List<ExchangeRateDto> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getDate() {
        return date;
    }

    public String getBank() {
        return bank;
    }

    public Integer getBaseCurrency() {
        return baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public List<ExchangeRateDto> getExchangeRate() {
        return exchangeRate;
    }
}
