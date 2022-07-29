package com.expressbank.controller;

import com.expressbank.dto.*;
import com.expressbank.entity.Card;
import com.expressbank.entity.Client;
import com.expressbank.entity.Currency;
import com.expressbank.entity.Payment;
import com.expressbank.service.CardService;
import com.expressbank.service.ClientService;
import com.expressbank.service.CurrencyService;
import com.expressbank.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/data")
public class BankController {

    private final ClientService clientService;
    private final CardService cardService;
    private final CurrencyService currencyService;
    private final PaymentService paymentService;

    //Post
    @PostMapping("/save-client")
    public ResponseEntity<ResponseDTO> saveClient(@RequestBody ClientDTO clientDTO){
        Client client = Client.builder()
                .name(clientDTO.getName())
                .surname(clientDTO.getSurname())
                .birthdate(clientDTO.getBirthdate())
                .card(cardService.findCardByClientId(clientDTO.getId())).build();

        clientService.save(client);

        return new ResponseEntity<>(ResponseDTO.of(200, HttpStatus.OK, "Saved is successfully!", client),
                HttpStatus.OK);
    }

    @PostMapping("/save-card")
    public ResponseEntity<ResponseDTO> saveCard(@RequestBody CardDTO cardDTO){
        Card card = Card.builder()
                .cardNumber(cardDTO.getCardNumber())
                .expireDate(cardDTO.getExpireDate())
//                .client(clientService.findById(cardDTO.getClientId()).get())
                .build();

        if (cardService.isDuplicate(cardDTO.getCardNumber())){
            return new ResponseEntity<>(ResponseDTO.of(400, HttpStatus.BAD_REQUEST, "Card is duplicate!"),
                    HttpStatus.BAD_REQUEST);
        }
        cardService.save(card);

        return new ResponseEntity<>(ResponseDTO.of(200, HttpStatus.OK, "Saved is successfully!", card),
                HttpStatus.OK);
    }

    @PostMapping("/save-currency")
    public ResponseEntity<ResponseDTO> saveCurrency(@RequestBody CurrencyDTO currencyDTO){
        Currency currency = Currency.builder()
                .currency(currencyDTO.getCurrency()).build();
        currencyService.save(currency);

        return new ResponseEntity<>(ResponseDTO.of(200, HttpStatus.OK, "Saved is successfully!", currency),
                HttpStatus.OK);
    }

    @PostMapping("/save-payment")
    public ResponseEntity<ResponseDTO> savePayment(@RequestBody PaymentDTO paymentDTO){
        Payment payment = Payment.builder()
                .card(paymentDTO.getCard())
                .currency(paymentDTO.getCurrency())
                .terminalId(paymentDTO.getTerminalId())
                .build();
        paymentService.save(payment);

        return new ResponseEntity<>(ResponseDTO.of(200, HttpStatus.OK, "Payed is successfully!", payment),
                HttpStatus.OK);
    }

    //Get
    @GetMapping("/get-client")
    public ResponseEntity<ResponseDTO> getClient(){
        List<Client> clients = clientService.findAll();
        if (clients==null || clients.isEmpty()){
            return new ResponseEntity<>(ResponseDTO.of(400, HttpStatus.BAD_REQUEST, "Clients is null!"),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ResponseDTO.of(200, HttpStatus.OK, "Successfully!", clients),
                HttpStatus.OK);
    }

    @GetMapping("/get-card")
    public ResponseEntity<ResponseDTO> getCard(){
        List<Card> cards = cardService.findAll();
        if (cards==null || cards.isEmpty()){
            return new ResponseEntity<>(ResponseDTO.of(400, HttpStatus.BAD_REQUEST, "Cards is null!"),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ResponseDTO.of(200, HttpStatus.OK, "Successfully!", cards),
                HttpStatus.OK);
    }

    @GetMapping("/get-currency")
    public ResponseEntity<ResponseDTO> getCurrency(){
        List<Currency> currencies = currencyService.findAll();
        if (currencies==null || currencies.isEmpty()){
            return new ResponseEntity<>(ResponseDTO.of(400, HttpStatus.BAD_REQUEST, "Currencies is null!"),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ResponseDTO.of(200, HttpStatus.OK, "Successfully!", currencies),
                HttpStatus.OK);
    }

    @GetMapping("/get-payment")
    public ResponseEntity<ResponseDTO> getPayment(){
        List<Payment> payments = paymentService.findAll();
        if (payments==null || payments.isEmpty()){
            return new ResponseEntity<>(ResponseDTO.of(400, HttpStatus.BAD_REQUEST, "Payments is null!"),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ResponseDTO.of(200, HttpStatus.OK, "Successfully!", payments),
                HttpStatus.OK);
    }
    //Update
    //Delete
}
