package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.ApprovalDTO;
import com.fiap.transactionsAPI.dto.TransactionDTO;

public interface TransactionService {

    public ApprovalDTO authorize(TransactionDTO transactionDTO);
}
