package com.expressbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class CheckCardDTO {
    private String birthdate;
    private String lastEightNumber;
    private Double addedMoney;

    public CheckCardDTO() {
    }
}
