package com.fiap.transactionsAPI.dto;

import com.fiap.transactionsAPI.entity.CardAccountEntity;

public class CardAccountDTO {

    private String id;
    private Double accountBalance;
    private Double accountLimit;

    public CardAccountDTO() {}

    public CardAccountDTO(CardAccountEntity cardAccount){
        this.id = cardAccount.getId();
        this.accountBalance = cardAccount.getAccountBalance();
        this.accountLimit = cardAccount.getAccountLimit();
    }


    public Double getAccountBalance() {
        return accountBalance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Double getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(Double accountLimit) {
        this.accountLimit = accountLimit;
    }
}
