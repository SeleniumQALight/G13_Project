package org.api.dto.responseDto;

public class PrivatExchangeDto {
    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private ExchangeRateDto[] exchangeRate;

    public PrivatExchangeDto() {
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

    public Integer getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Integer baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

    public ExchangeRateDto[] getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRateDto[] exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
