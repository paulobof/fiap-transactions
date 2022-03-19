package com.fiap.transactionsAPI.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("invoice")
public class InvoiceEntity {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private LocalDate issuanceDate;
    private String barcode;
    private LocalDate dueDate;
    private Double fullValue;
    private Double minimalValue;
    private LocalDate closingDate;

    private InvoiceItemEntity invoiceItemEntity;

}
