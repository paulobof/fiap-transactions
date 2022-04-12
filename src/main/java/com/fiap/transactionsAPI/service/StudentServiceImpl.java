package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.StudentDTO;
import com.fiap.transactionsAPI.entity.StudentEntity;
import com.fiap.transactionsAPI.repository.StudentRepository;
import com.fiap.transactionsAPI.service.exception.ObjectNotFoundException;
import com.fiap.transactionsAPI.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }


    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(StudentDTO::new)
                .collect(Collectors.toList());
    }

    public StudentDTO findById(Long id) {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(id);
        StudentEntity studentEntity = optionalStudentEntity.orElseThrow(
                () -> new ObjectNotFoundException(Constants.OBJETO_NAO_ENCONTRADO));
        return new StudentDTO(studentEntity);
    }

    public StudentDTO insert(StudentDTO studentDTO) {
        StudentEntity entity = new StudentEntity(studentDTO);

        if (studentDTO.getRa() == null)
            studentDTO.setRa(generateRa());
        else if(studentRepository.findById(studentDTO.getRa()).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estudante já existente!");

        return new StudentDTO(studentRepository.insert(entity));

    }

    public StudentEntity create(StudentDTO studentDTO) {
        StudentEntity entity = new StudentEntity(studentDTO);

        if (studentDTO.getRa() == null)
            studentDTO.setRa(generateRa());
        else if(studentRepository.findById(studentDTO.getRa()).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estudante já existente!");

        StudentEntity studentInserido = studentRepository.insert(entity);
        return studentInserido;
    }


    private Long generateRa() {
        Random random = new Random();
        int i = random.nextInt(899999) + 100000;
        return (long) i;
    }

    public StudentDTO update(StudentDTO studentDTO) {
        findById(studentDTO.getRa());
        StudentEntity studentEntity = new StudentEntity(studentDTO);
        return new StudentDTO(studentRepository.save(studentEntity));
    }

    public Long delete(Object id) {
        Long cardNumber = null;
        try {
            Optional<StudentEntity> dto = studentRepository.findByRa(Long.parseLong((String) id));
            cardNumber = dto.get().getCard().getCardNumber();
            studentRepository.delete(dto.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        } catch (Exception e) {
            this.findById((Long) id);
            studentRepository.deleteById((Long) id);
        }
        return cardNumber;
    }

    public StudentDTO findByRa(Long ra) {
        Optional<StudentEntity> optionalStudent = studentRepository.findByRa(ra);
        return new StudentDTO(optionalStudent.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }


}
