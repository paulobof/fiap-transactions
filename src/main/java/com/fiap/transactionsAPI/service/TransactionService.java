package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.CardDTO;
import com.fiap.transactionsAPI.dto.TransactionDTO;

public interface TransactionService {

    public TransactionDTO authorize(CardDTO cardDTO);
}
