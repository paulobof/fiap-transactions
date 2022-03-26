package com.fiap.transactionsAPI.dto;

import com.fiap.transactionsAPI.enums.CardFlagEnum;

import java.time.LocalDate;

public class CardDTO {

    private String name;
    private Long cardNumber;
    private Integer securityCode;
    private LocalDate expirationDate;
    private CardFlagEnum cardFlagEnum;

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

    public CardFlagEnum getCardFlagEnum() {
        return cardFlagEnum;
    }

    public void setCardFlagEnum(CardFlagEnum cardFlagEnum) {
        this.cardFlagEnum = cardFlagEnum;
    }
}
