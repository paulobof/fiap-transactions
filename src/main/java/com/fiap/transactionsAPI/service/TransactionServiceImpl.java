package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.ApprovalDTO;
import com.fiap.transactionsAPI.dto.InvoiceItemDTO;
import com.fiap.transactionsAPI.dto.TransactionDTO;
import com.fiap.transactionsAPI.entity.CardEntity;
import com.fiap.transactionsAPI.entity.InvoiceEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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


        if (optCardEntity.isPresent() &&
                optCardEntity.get().equals(new CardEntity(transactionDTO.getCardDTO()))) {
//                optCardEntity.get().getSecurityCode().equals(transactionDTO.getCardDTO().getSecurityCode())) {
            if (checkBalance(optCardEntity.get(), transactionDTO.getPurchaseDTO())) {
                InvoiceEntity updatedInvoice = cardService.createOrUpdateInvoice(optCardEntity.get().getInvoiceEntityList(), transactionDTO.getPurchaseDTO());
                cardService.update(optCardEntity.get(), updatedInvoice, transactionDTO.getPurchaseDTO());
                approvalDTO.approvalSucess();
            } else
                approvalDTO.approvalDenied();
        } else
            approvalDTO.approvalFailed();

        return approvalDTO;
    }

    private Boolean checkBalance(CardEntity card, InvoiceItemDTO purchaseDTO) {
        List<InvoiceEntity> currentInvoiceList = cardService.getCurrentInvoiceList(card.getInvoiceEntityList());
        if (currentInvoiceList.isEmpty()){
            card.getCardAccount().setAccountBalance(1000.0);
            card.getCardAccount().setAccountBalance(1000.0);
        }
        return card.getCardAccount().getAccountBalance() > 0.0
                && card.getCardAccount().getAccountBalance() >= purchaseDTO.getPurchaseValue();
    }

}
