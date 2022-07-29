package com.expressbank.service;

import com.expressbank.entity.Card;
import com.expressbank.entity.Client;
import com.expressbank.repository.CardRepo;
import com.expressbank.repository.ClientRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepo clientRepo;

    public void save(Client client){
        clientRepo.save(client);
    }

    public Optional<Client> findById(int id){
        return clientRepo.findById(id);
    }

    public Client findByCardId(int cardId){
        return clientRepo.findClientByCardId(cardId);
    }

    public List<Client> findAll(){
        return clientRepo.findAll();
    }

    public void remove(int id){
        Optional<Client> client = findById(id);
        if (client!=null){
            clientRepo.delete(client.get());
        }
    }

    @SneakyThrows
    public Date strConvertDate(String birthdateStr){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        java.util.Date date = sdf.parse(birthdateStr);

        java.sql.Date birthdate = new java.sql.Date(date.getTime());

        return birthdate;
    }
}
