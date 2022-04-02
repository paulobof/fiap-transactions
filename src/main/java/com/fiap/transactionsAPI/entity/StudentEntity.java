package com.fiap.transactionsAPI.entity;

import com.fiap.transactionsAPI.dto.StudentDTO;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Document("student")
public class StudentEntity implements Serializable {

    private static final long serialVersionUID = 1;

    private String name;
    private String email;

    @MongoId(FieldType.INT64)
    private Long ra;

    @DocumentReference(lazy = false)
    @Indexed(unique = true)
    private CardEntity card;

    public StudentEntity() {
    }

    public StudentEntity(StudentDTO studentDTO) {
        this.ra = studentDTO.getRa();
        this.name = studentDTO.getName();
        this.email = studentDTO.getEmail();
        this.card = studentDTO.getCard();
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
