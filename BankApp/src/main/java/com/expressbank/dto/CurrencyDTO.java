package com.expressbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CurrencyDTO {
    private Integer id;
    private String currency;

    public CurrencyDTO() {}
}
