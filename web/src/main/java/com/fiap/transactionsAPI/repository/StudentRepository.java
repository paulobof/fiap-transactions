package com.fiap.transactionsAPI.repository;

import com.fiap.transactionsAPI.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<StudentEntity, Long> {

    Optional<StudentEntity> findByRa(Long ra);

    @Query("{'card': {$eq: null}}")
    List<StudentEntity> getStudentsWithoutCard();
}
