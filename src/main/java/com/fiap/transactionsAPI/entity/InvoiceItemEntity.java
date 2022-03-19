package com.fiap.transactionsAPI.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("invoiceItem")
public class InvoiceItemEntity {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private LocalDate purchaseDate;
    private String establishment;
    private Double itemValue;

    public InvoiceItemEntity() {
    }

    public InvoiceItemEntity(LocalDate purchaseDate, String establishment, Double itemValue) {
        this.purchaseDate = purchaseDate;
        this.establishment = establishment;
        this.itemValue = itemValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public Double getItemValue() {
        return itemValue;
    }

    public void setItemValue(Double itemValue) {
        this.itemValue = itemValue;
    }
}
