package com.fiap.transactionsAPI.controller;

import com.fiap.transactionsAPI.dto.StudentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StundentController {

    @GetMapping
    public List<StudentDTO> getStudents(){
        StudentDTO studentDTO = new StudentDTO();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        studentDTOS.add(studentDTO);
        return studentDTOS;
    }

    @PostMapping
    public StudentDTO insertStudent(@RequestBody StudentDTO studentDTO){
        return new StudentDTO();
    }

    @PutMapping(value = "{id}")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable String id){
        System.out.println("Chegou aqui! -> " + id);
        return null;
    }

    @DeleteMapping
    public void deleteStudent(@PathVariable String id){
        // TODO: 06/03/2022 implementar a busca pelo id e depois o delete
    }




}
