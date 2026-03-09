package org.api.dto.responseDto;

public class PBBaseCurrency {
    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private PBExchangeRatesDto[] exchangeRate;

    public PBBaseCurrency() {
    }

    public PBBaseCurrency(String date, String bank, Integer baseCurrency, String baseCurrencyLit, PBExchangeRatesDto[] exchangeRate) {
        this.date = date;
        this.bank = bank;
        this.baseCurrency = baseCurrency;
        this.baseCurrencyLit = baseCurrencyLit;
        this.exchangeRate = exchangeRate;
    }

    public Integer getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Integer baseCurrency) {
        this.baseCurrency = baseCurrency;
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

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

    public PBExchangeRatesDto[] getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(PBExchangeRatesDto[] exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "PBBaseCurrency{" +
                "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
