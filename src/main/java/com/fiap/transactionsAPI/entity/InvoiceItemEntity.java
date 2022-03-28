package com.fiap.transactionsAPI.entity;

import com.fiap.transactionsAPI.dto.InvoiceItemDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("invoiceItem")
public class InvoiceItemEntity {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private LocalDateTime purchaseDate;
    private String establishment;
    private Double itemValue;

    public InvoiceItemEntity() {
    }

    public InvoiceItemEntity(LocalDateTime purchaseDate, String establishment, Double itemValue) {
        this.purchaseDate = purchaseDate;
        this.establishment = establishment;
        this.itemValue = itemValue;
    }

    public InvoiceItemEntity(InvoiceItemDTO purchaseItem) {
        this.establishment = purchaseItem.getEstablishment();
        this.itemValue = purchaseItem.getPurchaseValue();
        this.purchaseDate = purchaseItem.getPurchaseDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
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
