package com.fiap.transactionsAPI.repository;

import com.fiap.transactionsAPI.entity.InvoiceItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceItemRepository extends MongoRepository<InvoiceItemEntity, String> {


}
