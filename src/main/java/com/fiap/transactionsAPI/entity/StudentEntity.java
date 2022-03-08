package com.fiap.transactionsAPI.entity;

import com.fiap.transactionsAPI.dto.StudentDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document("student")
public class StudentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String email;

    @Id
    private Long ra;

    public StudentEntity(){}

    public StudentEntity(StudentDTO studentDTO){
        this.ra = studentDTO.getRa();
        this.name = studentDTO.getName();
        this.email = studentDTO.getEmail();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return ra.equals(that.ra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ra);
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", ra=" + ra +
                '}';
    }
}
