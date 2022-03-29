package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.entity.InvoiceEntity;
import com.fiap.transactionsAPI.entity.InvoiceItemEntity;

public interface InvoiceService {

    public InvoiceEntity create(InvoiceItemEntity invoiceItemEntity);

    InvoiceEntity update(InvoiceEntity invoiceEntity, InvoiceItemEntity invoiceItemEntity);
}
