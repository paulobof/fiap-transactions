package com.fiap.transactionsAPI.dto;

import com.fiap.transactionsAPI.entity.CardEntity;
import com.fiap.transactionsAPI.entity.StudentEntity;

public class StudentDTO {

    private String name;
    private String email;
    private Long ra;
    private CardEntity card;

    public StudentDTO() {
    }

    public StudentDTO(StudentEntity entity){
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.ra = entity.getRa();
        this.card = entity.getCard();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
    }


    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }
}
