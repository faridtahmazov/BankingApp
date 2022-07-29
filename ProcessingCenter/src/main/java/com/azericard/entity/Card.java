package com.azericard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String cardNumber;

    @Temporal(TemporalType.DATE)
    private Date expireDate;
    @OneToOne(mappedBy = "card")
    private Client client;

    public Card() {}
}
