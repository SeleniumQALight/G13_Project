package org.api.dto.responseDto;
import lombok.Data;
import java.util.List;

    @Data
    public class ExchangeRatesResponseDto {
        private String date;
        private String bank;
        private String baseCurrencyLit;
        private int baseCurrency;

        private List<ExchangeRateDto> exchangeRate;
}