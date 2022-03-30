package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.CardAccountDTO;
import com.fiap.transactionsAPI.entity.CardAccountEntity;

public interface CardAccountService {

    public CardAccountDTO update(CardAccountEntity cardAccountEntity);
}
