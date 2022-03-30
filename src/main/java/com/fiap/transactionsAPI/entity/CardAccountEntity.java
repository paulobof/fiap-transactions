package com.fiap.transactionsAPI.entity;

import com.fiap.transactionsAPI.dto.CardAccountDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("cardAccount")
public class CardAccountEntity {

    private static final long serivalVersionUID = 1L;

    @Id
    private String id;

    private Double accountBalance;
    private Double accountLimit;

    public CardAccountEntity(){}

    public CardAccountEntity(String id, Double accountBalance, Double accountLimit) {
        this.id = id;
        this.accountBalance = accountBalance;
        this.accountLimit = accountLimit;
    }

    public CardAccountEntity(CardAccountDTO cardAccountDTO){
        this.accountBalance = cardAccountDTO.getAccountBalance();
        this.accountLimit = cardAccountDTO.getAccountLimit();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardAccountEntity that = (CardAccountEntity) o;
        return Objects.equals(id, that.id);
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

    public Double getAccountBalance() {
        return accountBalance;
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
