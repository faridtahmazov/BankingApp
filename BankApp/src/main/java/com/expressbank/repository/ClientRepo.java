package com.expressbank.repository;

import com.expressbank.entity.Card;
import com.expressbank.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    Client findClientByCardId(int id);
}
