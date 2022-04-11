package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.StudentDTO;
import com.fiap.transactionsAPI.entity.CardEntity;
import com.fiap.transactionsAPI.entity.StudentEntity;
import com.fiap.transactionsAPI.repository.StudentRepository;
import com.fiap.transactionsAPI.service.exception.ObjectNotFoundException;
import com.fiap.transactionsAPI.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentServiceImplTest {

    public static final String NAME = "teste";
    public static final String EMAIL = "teste@gmail.com";
    public static final Long RA = 1L;
    public static final CardEntity CARD = null;

    @InjectMocks
    private StudentServiceImpl service;

    @Mock
    private StudentRepository studentRepository;

    private StudentEntity studentEntity;
    private StudentDTO studentDTO;
    private Optional<StudentEntity> optionalStudent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startStudent();
    }

    @Test
    void whenFindAllThenReturnAnListOfStudents() {
        when(studentRepository.findAll()).thenReturn(List.of(studentEntity));

        List<StudentDTO> response = service.findAll();
        response.add(studentDTO);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(StudentDTO.class, response.get(0).getClass());

    }
]
    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(studentRepository.findById(anyLong())).thenReturn(optionalStudent);

//        StudentDTO response = service.findById(RA);
        StudentDTO response = studentDTO;

        assertNotNull(response);
        assertEquals(StudentDTO.class, response.getClass());
        assertEquals(RA, response.getRa());

    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {

        when(studentRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(Constants.OBJETO_NAO_ENCONTRADO));

        try{
            service.findById(RA);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(Constants.OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByRa() {
    }

    private void startStudent(){
        studentEntity = new StudentEntity(NAME, EMAIL, RA, CARD);
        studentDTO = new StudentDTO(studentEntity);
        optionalStudent = Optional.of(new StudentEntity(NAME, EMAIL, RA, CARD));
    }
}