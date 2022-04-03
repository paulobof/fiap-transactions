package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    public List<StudentDTO> findAll();

    public StudentDTO findById(String id);

    public StudentDTO insert(StudentDTO studentDTO);

    public StudentDTO update(StudentDTO studentDTO);

    public Long delete(Object id);

    public StudentDTO findByRa(Long ra);
}
