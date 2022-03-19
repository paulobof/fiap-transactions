package com.fiap.transactionsAPI.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

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

    private List<InvoiceItemEntity> invoiceItemEntity;

    public InvoiceEntity() {
    }

    public InvoiceEntity(LocalDate issuanceDate, String barcode, LocalDate dueDate, Double fullValue, Double minimalValue, LocalDate closingDate, List<InvoiceItemEntity> invoiceItemEntity) {
        this.issuanceDate = issuanceDate;
        this.barcode = barcode;
        this.dueDate = dueDate;
        this.fullValue = fullValue;
        this.minimalValue = minimalValue;
        this.closingDate = closingDate;
        this.invoiceItemEntity = invoiceItemEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(LocalDate issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Double getFullValue() {
        return fullValue;
    }

    public void setFullValue(Double fullValue) {
        this.fullValue = fullValue;
    }

    public Double getMinimalValue() {
        return minimalValue;
    }

    public void setMinimalValue(Double minimalValue) {
        this.minimalValue = minimalValue;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public List<InvoiceItemEntity> getInvoiceItemEntity() {
        return invoiceItemEntity;
    }

    public void setInvoiceItemEntity(List<InvoiceItemEntity> invoiceItemEntity) {
        this.invoiceItemEntity = invoiceItemEntity;
    }
}
