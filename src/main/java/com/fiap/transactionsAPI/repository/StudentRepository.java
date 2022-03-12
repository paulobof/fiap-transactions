package com.fiap.transactionsAPI.repository;

import com.fiap.transactionsAPI.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<StudentEntity, String> {

    Optional<StudentEntity> findByRa(Long ra);
}
