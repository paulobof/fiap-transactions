package com.fiap.transactionsAPI.repository;

import com.fiap.transactionsAPI.entity.CardEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CardRepository extends MongoRepository<CardEntity, String> {

     Optional<CardEntity> findByCardNumber(Long cardNumber);
}
