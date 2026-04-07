package org.api;

import lombok.*;

@Data
public class CurrencyDto {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}