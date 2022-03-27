package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.AprovalDTO;
import com.fiap.transactionsAPI.dto.InvoiceItemDTO;
import com.fiap.transactionsAPI.dto.TransactionDTO;
import com.fiap.transactionsAPI.entity.CardEntity;
import com.fiap.transactionsAPI.enums.StatusEnum;
import com.fiap.transactionsAPI.repository.CardRepository;
import com.fiap.transactionsAPI.repository.InvoiceRepository;
import com.fiap.transactionsAPI.utils.Constants;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private CardRepository cardRepository;
    private CardServiceImpl cardService;
    private InvoiceRepository invoiceRepository;

    public TransactionServiceImpl(CardRepository cardRepository,
                                  CardServiceImpl cardService,
                                  InvoiceRepository invoiceRepository){

        this.cardRepository = cardRepository;
        this.cardService = cardService;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public AprovalDTO authorize(TransactionDTO transactionDTO) {

//         Consultar se existe o cartão
        Optional<CardEntity> optCardEntity = cardService.findCard(transactionDTO.getCardDTO());
        AprovalDTO aprovalDTO = new AprovalDTO();

//        Caso existir, mando gerar a fatura e monto o obj de retorno

        if(optCardEntity.isPresent())
            aprovalDTO = checkBalance(optCardEntity.get(), transactionDTO.getPurchaseDTO());
        else
            aprovalDTO = aprovalFailed();

        return aprovalDTO;
    }

    private AprovalDTO checkBalance(CardEntity card, InvoiceItemDTO purchaseDTO) {
        AprovalDTO aprovalDTO = new AprovalDTO();
        if(card.getCardAccount().getAccountBalance() > 0.0
            && card.getCardAccount().getAccountBalance() >= purchaseDTO.getPurchaseValue())
            aprovalSucess(aprovalDTO);
        else
            aprovalDTO = aprovalFailed();

        return aprovalDTO;
    }

    private AprovalDTO aprovalFailed() {
        AprovalDTO aprovalDTO = new AprovalDTO();
        aprovalDTO.setApprovalTime(LocalDateTime.now());
        aprovalDTO.setStatusEnum(StatusEnum.NOT_APROVED);
        aprovalDTO.setMessage(Constants.INSUFFICIENT_BALANCE);
        return aprovalDTO;
    }

    private void aprovalSucess(AprovalDTO aprovalDTO) {
        aprovalDTO.setApprovalTime(LocalDateTime.now());
        aprovalDTO.setStatusEnum(StatusEnum.APROVED);
        aprovalDTO.setMessage(Constants.PURCHASE_MADE_SUCCESSFULLY);
    }
}
