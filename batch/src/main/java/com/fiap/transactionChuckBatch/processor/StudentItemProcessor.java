package com.fiap.transactionChuckBatch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.fiap.transactionChuckBatch.entity.StudentEntity;
import com.fiap.transactionChuckBatch.dto.StudentDTO;


public class StudentItemProcessor implements ItemProcessor<StudentDTO, StudentEntity> {
    private static final Logger log = LoggerFactory.getLogger(StudentItemProcessor.class);

    @Override
    public StudentEntity process(StudentDTO item) throws Exception {

        log.info("processing user data.....{}", item);

        StudentEntity transformedStudentEntity = new StudentEntity();
        transformedStudentEntity.setName(item.getName());
        transformedStudentEntity.setRa(item.getRa());
        transformedStudentEntity.setEmail("rm" + item.getRa() + "@gmail.com");
        transformedStudentEntity.setCard("");
        return transformedStudentEntity;
    }

}