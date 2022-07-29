package com.expressbank.dto;

import com.expressbank.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;

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
