package com.azericard.service;

import com.azericard.entity.Card;
import com.azericard.entity.Client;
import com.azericard.repository.CardRepo;
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

    public boolean check(Date birthdate, String lastEightNumber){
        boolean isHaveBirthdate = false;
        boolean isHaveCard = false;

        for (Client client: clientService.findAll()){
            if (client.getBirthdate().equals(birthdate)){
                isHaveBirthdate=true;
            }
        }

        for (Card card: findAll()){
            String cardNumber = card.getCardNumber();
            String cleanCard[] = cardNumber.split("-");
            String checkLastEightNumber = "";
            System.out.println(cleanCard.length);
            for (int i=4; i<cleanCard.length; i++){
                checkLastEightNumber+=cleanCard[i];
            }

            if (checkLastEightNumber.equals(lastEightNumber)){
                isHaveCard=true;
            }
        }

        return isHaveBirthdate&&isHaveCard;
    }
}
