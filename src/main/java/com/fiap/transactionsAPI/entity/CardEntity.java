package com.fiap.transactionsAPI.entity;

import com.fiap.transactionsAPI.enums.CardFlagEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.List;

@Document("card")
public class CardEntity {

    private static final long serivalVersionUID = 1;

    @Id
    private String id;

    private String name;
    private CardFlagEnum cardFlag;
    private Long cardNumber;
    private Integer securityCode;
    private LocalDate expirationDate;
    private CardAccountEntity cardAccount;

    @DocumentReference
    private List<InvoiceEntity> invoiceEntityList;

    public CardEntity() {

    }

    public CardEntity(String name, CardFlagEnum cardFlag, Long cardNumber, Integer securityCode, LocalDate expirationDate, CardAccountEntity cardAccount, List<InvoiceEntity> invoiceEntityList) {
        this.name = name;
        this.cardFlag = cardFlag;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.expirationDate = expirationDate;
        this.cardAccount = cardAccount;
        this.invoiceEntityList = invoiceEntityList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(Integer securityCode) {
        this.securityCode = securityCode;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CardAccountEntity getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(CardAccountEntity cardAccount) {
        this.cardAccount = cardAccount;
    }

    public List<InvoiceEntity> getInvoiceEntityList() {
        return invoiceEntityList;
    }

    public void setInvoiceEntityList(List<InvoiceEntity> invoiceEntityList) {
        this.invoiceEntityList = invoiceEntityList;
    }

    public CardFlagEnum getCardFlag() {
        return cardFlag;
    }

    public void setCardFlag(CardFlagEnum cardFlag) {
        this.cardFlag = cardFlag;
    }
}
