package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.AprovalDTO;
import com.fiap.transactionsAPI.dto.TransactionDTO;

public interface TransactionService {

    public AprovalDTO authorize(TransactionDTO transactionDTO);
}
