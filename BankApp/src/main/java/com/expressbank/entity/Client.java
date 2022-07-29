package com.expressbank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @OneToOne
    @JoinColumn(name = "cardId")
    private Card card;

    public Client() {}

}
