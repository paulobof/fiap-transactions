package com.fiap.transactionsAPI.repository;

import com.fiap.transactionsAPI.entity.InvoiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<InvoiceEntity, String> {


}
