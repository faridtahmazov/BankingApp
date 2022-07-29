package com.azericard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ClientDTO {
    private Integer id;
    private String name;
    private String surname;
    private Date birthdate;
    private Integer cardId;

    public ClientDTO() {}
}
