package com.fiap.transactionsAPI.repository;

import com.fiap.transactionsAPI.entity.CardAccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardAccountRepository extends MongoRepository<CardAccountEntity, String> {


}
