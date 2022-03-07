package com.fiap.transactionsAPI.dto;

import com.fiap.transactionsAPI.entity.StudentEntity;

public class StudentDTO {

    private String name;
    private String email;
    private Long ra;

    public StudentDTO() {
    }

    public StudentDTO(StudentEntity entity){
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.ra = entity.getRa();
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


}
