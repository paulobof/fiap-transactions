package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.CardDTO;
import com.fiap.transactionsAPI.entity.CardEntity;

import java.util.Optional;

public interface CardService {

    public Optional<CardEntity> findCard(CardDTO cardDTO);
}
