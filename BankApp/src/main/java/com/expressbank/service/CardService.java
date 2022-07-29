package com.expressbank.service;

import com.expressbank.entity.Card;
import com.expressbank.entity.Client;
import com.expressbank.repository.CardRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepo cardRepo;

    private final ClientService clientService;

    public void save(Card card){
        cardRepo.save(card);
    }

    public Optional<Card> findById(int id){
        return cardRepo.findById(id);
    }

    public List<Card> findAll(){
        return cardRepo.findAll();
    }

    public Card findByCardNumber(String cardNumber){
        return cardRepo.findCardsByCardNumber(cardNumber);
    }

    public Card findByLastCardEightNumber(String lastEightNumber){
        return cardRepo.findCardByLastEightNumber(lastEightNumber);
    }

    public Card findCardByClientId(int id){
        return cardRepo.findCardByClientId(id);
    }

    public void remove(int id){
        Optional<Card> card = findById(id);
        if (card!=null){
            cardRepo.delete(card.get());
        }
    }

    public boolean isDuplicate(String cardNumber){
        boolean isHave = false;
        for (Card card: findAll()){
            if (card.getCardNumber().equals(cardNumber)){
                isHave = true;
            }
        }
        return isHave;
    }

    public boolean check(Date birthdate, String lastEightNumberREQUEST){
        boolean isHaveBirthdate = false;
        boolean isHaveCard = false;

        System.out.println("birthdateREQUEST: " + birthdate);
        System.out.println();
        for (Client client: clientService.findAll()){
            System.out.println("birthdateREQUEST: " + birthdate);
            System.out.println("birthdate: " + client.getBirthdate());
            if (client.getBirthdate().equals(birthdate)){
                isHaveBirthdate=true;
                break;
            }
        }
        System.out.println("Last eight number request: " + lastEightNumberREQUEST);

        for (Card card: findAll()){
            String cardNumber = card.getCardNumber();
            char[] checkLastEightNumber = cardNumber.toCharArray();
            String lastEightNumber = "";

            for (int i=6; i<16; i++){
                lastEightNumber+=checkLastEightNumber[i];
            }
            System.out.println("Last eight number: " + lastEightNumber);

            if (lastEightNumber.equals(lastEightNumberREQUEST)){
                System.out.println("Card is correct!");
                isHaveCard=true;
                break;
            }
        }

        return isHaveBirthdate&&isHaveCard;
    }
}
