package org.api.dto.responseDto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRate {
    @JsonProperty("ccy")
    private String ccy;
    @JsonProperty("base_ccy")
    private String baseCcy;
    @JsonProperty("buy")
    private String buy;
    @JsonProperty("sale")
    private String sale;

}
