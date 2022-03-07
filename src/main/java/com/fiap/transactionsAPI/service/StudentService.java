package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.StudentDTO;
import com.fiap.transactionsAPI.entity.StudentEntity;
import com.fiap.transactionsAPI.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){

        this.studentRepository = studentRepository;
    }


    public List<StudentDTO> findAll(){
        // TODO: 06/03/2022 implement repository
        return null;
    }

    public StudentDTO findById(String id){
       Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(id);
       StudentEntity studentEntity = optionalStudentEntity.orElseThrow(RuntimeException::new);
       return new StudentDTO(studentEntity);
    }

    public StudentDTO insert(StudentDTO studentDTO){
        return null;
    }

    public StudentDTO update(StudentDTO studentDTO){
        return null;
    }

    public void delete(String id){
    }

}
