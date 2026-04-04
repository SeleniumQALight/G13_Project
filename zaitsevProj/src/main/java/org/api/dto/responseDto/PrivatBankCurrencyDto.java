package org.api.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrivatBankCurrencyDto {

    private String ccy;

    @JsonProperty("base_ccy")
    private String baseCcy;

    private String buy;
    private String sale;
}