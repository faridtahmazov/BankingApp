package com.expressbank.service;

import com.expressbank.entity.Card;
import com.expressbank.entity.Currency;
import com.expressbank.repository.CardRepo;
import com.expressbank.repository.CurrencyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepo currencyRepo;

    public void save(Currency currency){
        currencyRepo.save(currency);
    }

    public Optional<Currency> findById(int id){
        return currencyRepo.findById(id);
    }

    public List<Currency> findAll(){
        return currencyRepo.findAll();
    }

    public void remove(int id){
        Optional<Currency> currency = findById(id);
        if (currency!=null){
            currencyRepo.delete(currency.get());
        }
    }
}
