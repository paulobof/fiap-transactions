package com.fiap.transactionsAPI.dto;

public class TransactionDTO {

    private InvoiceItemDTO purchaseDTO;
    private CardDTO cardDTO;

    public InvoiceItemDTO getPurchaseDTO() {
        return purchaseDTO;
    }

    public void setPurchaseDTO(InvoiceItemDTO purchaseDTO) {
        this.purchaseDTO = purchaseDTO;
    }

    public CardDTO getCardDTO() {
        return cardDTO;
    }

    public void setCardDTO(CardDTO cardDTO) {
        this.cardDTO = cardDTO;
    }
}
