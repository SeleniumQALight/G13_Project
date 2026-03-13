package org.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeResponseDto {

    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;
    private List<ExchangeRateDto> exchangeRate;

    public String getDate() {
        return date;
    }

    public String getBank() {
        return bank;
    }

    public int getBaseCurrency() {
        return baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public List<ExchangeRateDto> getExchangeRate() {
        return exchangeRate;
    }
}