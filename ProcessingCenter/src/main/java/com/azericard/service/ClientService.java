package com.azericard.service;

import com.azericard.entity.Client;
import com.azericard.repository.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
