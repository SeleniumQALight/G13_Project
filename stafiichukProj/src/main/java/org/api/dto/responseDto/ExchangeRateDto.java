package org.api.dto.responseDto;

import lombok.Data;

@Data
public class ExchangeRateDto {

    private String baseCurrency;
    private String currency;

    private Double saleRateNB;
    private Double purchaseRateNB;
    private Double saleRate;
    private Double purchaseRate;
}