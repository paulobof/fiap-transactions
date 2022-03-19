package com.fiap.transactionsAPI.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

@Document("card")
public class CardEntity {

    private static final long serivalVersionUID = 1;

    @Id
    private String id;

    private String name;
    private String cardFlag;
    private Long cardNumber;
    private Integer securityCode;
    private LocalDate expirationDate;
    private CardAccountEntity cardAccount;

    private InvoiceEntity invoiceEntity;

    public CardEntity() {

    }

    public CardEntity(String name, String cardFlag, Long cardNumber, Integer securityCode, LocalDate expirationDate) {
        this.name = name;
        this.cardFlag = cardFlag;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardFlag() {
        return cardFlag;
    }

    public void setCardFlag(String cardFlag) {
        this.cardFlag = cardFlag;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardEntity that = (CardEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CardAccountEntity getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(CardAccountEntity cardAccount) {
        this.cardAccount = cardAccount;
    }
}
