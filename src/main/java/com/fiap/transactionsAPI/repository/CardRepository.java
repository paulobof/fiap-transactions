package com.fiap.transactionsAPI.repository;

import com.fiap.transactionsAPI.entity.CardEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<CardEntity, String> {


}
