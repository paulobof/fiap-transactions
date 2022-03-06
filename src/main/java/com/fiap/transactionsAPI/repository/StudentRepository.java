package com.fiap.transactionsAPI.repository;

import com.fiap.transactionsAPI.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<StudentEntity, String> {
}
