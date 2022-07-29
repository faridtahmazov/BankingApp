package com.azericard.controller;

import com.azericard.dto.ResponseDTO;
import com.azericard.entity.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/data")
public class DataController {

    @PostMapping("/amount-processing")
    public ResponseEntity<ResponseDTO> amount(){
        if (GlobalData.isValidCardNumber){
            Card card = cardService.findByLastCardEightNumber(GlobalData.lastEightCardNumber);
            Object data[] = new Object[1];
            data[0] = card;
            data[1] = GlobalData.addedMoney;
            return new ResponseEntity<>(ResponseDTO.of(102, HttpStatus.PROCESSING,
                    "Request is sent. Wait for a response", data),
                    HttpStatus.PROCESSING);
        }

        return new ResponseEntity<>(ResponseDTO.of(400, HttpStatus.BAD_REQUEST,
                "Card info is wrong!"),
                HttpStatus.BAD_REQUEST);
    }
}
