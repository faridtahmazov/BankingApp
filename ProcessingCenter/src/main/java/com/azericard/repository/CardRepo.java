package com.azericard.repository;

import com.azericard.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepo extends JpaRepository<Card, Integer> {
    Card findCardByClientId(int id);
    Card findCardsByCardNumber(String cardNumber);

    @Query("SELECT c FROM Card c WHERE c.cardNumber LIKE %:lastEightNumber%")
    Card findCardByLastEightNumber(@Param("lastEightNumber") String lastEightNumber);
}
