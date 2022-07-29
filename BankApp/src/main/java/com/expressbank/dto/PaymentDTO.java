package com.expressbank.dto;

import com.expressbank.entity.Card;
import com.expressbank.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentDTO {
    private Integer id;
    private Integer terminalId;
    private Currency currency;
    private Card card;
}
