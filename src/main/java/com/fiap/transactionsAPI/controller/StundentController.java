package com.fiap.transactionsAPI.controller;

import com.fiap.transactionsAPI.dto.StudentDTO;
import com.fiap.transactionsAPI.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StundentController {

    private StudentService studentService;

    public StundentController(StudentService studentService){

        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> findAllStudents() {
        return studentService.findAll();
    }

    @GetMapping(value = {"id"})
    public StudentDTO findById(@PathVariable String id) {
        return new StudentDTO();
    }

    @PostMapping
    public StudentDTO insertStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO studentSaved = studentService.insert(studentDTO);
        return studentSaved;
    }

    @PutMapping(value = "{id}")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable String id) {
        System.out.println("Chegou aqui! -> " + id);
        return null;
    }

    @DeleteMapping
    public void deleteStudent(@PathVariable String id) {
        // TODO: 06/03/2022 implementar a busca pelo id e depois o delete
    }

}
