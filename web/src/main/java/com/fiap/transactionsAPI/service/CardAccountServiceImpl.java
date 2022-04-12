package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.CardAccountDTO;
import com.fiap.transactionsAPI.entity.CardAccountEntity;
import com.fiap.transactionsAPI.repository.CardAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class CardAccountServiceImpl implements CardAccountService {

    private final CardAccountRepository cardAccountRepository;

    public CardAccountServiceImpl(CardAccountRepository cardAccountRepository){
        this.cardAccountRepository = cardAccountRepository;
    }

    @Override
    public CardAccountDTO update(CardAccountEntity cardAccountEntity) {
       return new CardAccountDTO(cardAccountRepository.save(cardAccountEntity));
    }
}
