package org.api.dto.responseDto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class PBExchangeRatesTodayDto {
    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;


}
