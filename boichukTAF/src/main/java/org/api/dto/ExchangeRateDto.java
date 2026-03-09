package org.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateDto {

    private String baseCurrency;
    private String currency;

    private Double saleRateNB;
    private Double purchaseRateNB;
    private Double saleRate;
    private Double purchaseRate;

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public Double getSaleRateNB() {
        return saleRateNB;
    }

    public Double getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public Double getSaleRate() {
        return saleRate;
    }

    public Double getPurchaseRate() {
        return purchaseRate;
    }
}