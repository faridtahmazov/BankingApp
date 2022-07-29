package com.expressbank.controller;

import com.expressbank.dto.CheckCardDTO;
import com.expressbank.dto.ResponseDTO;
import com.expressbank.entity.Card;
import com.expressbank.global.GlobalData;
import com.expressbank.service.CardService;
import com.expressbank.service.ClientService;
import com.expressbank.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operation")
public class OperationController {
    private final CardService cardService;
    private final ClientService clientService;
    private final PaymentService paymentService;

    //post
    @PostMapping("/check-card")
    public ResponseEntity<ResponseDTO> checkCard(@RequestBody CheckCardDTO checkCardDTO){
        Date birthdate = clientService.strConvertDate(checkCardDTO.getBirthdate().toString());
        System.out.println("date: " + birthdate);
        GlobalData.isValidCardNumber = cardService.check(birthdate, checkCardDTO.getLastEightNumber());
        if (GlobalData.isValidCardNumber){
            GlobalData.lastEightCardNumber = checkCardDTO.getLastEightNumber();
            GlobalData.addedMoney = checkCardDTO.getAddedMoney();
            return new ResponseEntity<>(ResponseDTO.of(200, HttpStatus.OK, "Authentication Success!"),
                    HttpStatus.OK);
        }

        System.out.println(GlobalData.isValidCardNumber);
        System.out.println(GlobalData.lastEightCardNumber);
        System.out.println(GlobalData.addedMoney);
        return new ResponseEntity<>(ResponseDTO.of(400, HttpStatus.BAD_REQUEST, "Authentication is Error!"),
                HttpStatus.OK);
    }

    @GetMapping("/amount-processing")
    public ResponseEntity<ResponseDTO> amount(){
        System.out.println(GlobalData.isValidCardNumber);
        System.out.println(GlobalData.lastEightCardNumber);
        System.out.println(GlobalData.addedMoney);
        if (GlobalData.isValidCardNumber){
            Card card = cardService.findByLastCardEightNumber(GlobalData.lastEightCardNumber);
            Object data[] = new Object[2];
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
