package com.azericard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer terminalId;
    @OneToOne
    private Currency currency;
    @OneToOne
    private Card card;
    private Double amount;

    public Payment() {}
}
