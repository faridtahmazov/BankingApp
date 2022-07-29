package com.expressbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class CardDTO {
    private Integer id;
    @Column(unique = true)
    private String cardNumber;
    @Temporal(TemporalType.DATE)
    private Date expireDate;
    private Integer clientId;

    public CardDTO() {}
}
