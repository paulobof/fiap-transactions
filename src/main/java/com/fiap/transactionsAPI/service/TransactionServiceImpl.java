package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.ApprovalDTO;
import com.fiap.transactionsAPI.dto.InvoiceItemDTO;
import com.fiap.transactionsAPI.dto.TransactionDTO;
import com.fiap.transactionsAPI.entity.CardEntity;
import com.fiap.transactionsAPI.entity.InvoiceEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {


    private final CardService cardService;

    public TransactionServiceImpl(CardService cardService) {

        this.cardService = cardService;
    }

    @Override
    public ApprovalDTO authorize(TransactionDTO transactionDTO) {

        ApprovalDTO approvalDTO = new ApprovalDTO();
        Optional<CardEntity> optCardEntity = cardService.findCard(transactionDTO.getCardDTO());

        if (optCardEntity.isPresent()) {
            if (checkBalance(optCardEntity.get(), transactionDTO.getPurchaseDTO())) {
                InvoiceEntity updatedInvoice = cardService.createOrUpdateInvoice(optCardEntity.get().getInvoiceEntityList(), transactionDTO.getPurchaseDTO());
                cardService.update(optCardEntity.get(), updatedInvoice);
                approvalDTO.approvalSucess();
            } else
                approvalDTO.approvalDenied();
        } else
            approvalDTO.approvalFailed();

        return approvalDTO;
    }

    private Boolean checkBalance(CardEntity card, InvoiceItemDTO purchaseDTO) {
        return card.getCardAccount().getAccountBalance() > 0.0
                && card.getCardAccount().getAccountBalance() >= purchaseDTO.getPurchaseValue();
    }

}
