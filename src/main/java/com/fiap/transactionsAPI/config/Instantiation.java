package com.fiap.transactionsAPI.config;

import com.fiap.transactionsAPI.entity.CardEntity;
import com.fiap.transactionsAPI.repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class Instantiation implements CommandLineRunner {

    private CardRepository cardRepository;

    public Instantiation(CardRepository cardRepository){

        this.cardRepository = cardRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        cardRepository.deleteAll();

        CardEntity cardEntity = new CardEntity("Wesley Guimarães", "VISA", 775456789123L, 118, LocalDate.now());

        cardRepository.save(cardEntity);
    }
}
