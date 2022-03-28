package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.CardDTO;
import com.fiap.transactionsAPI.dto.InvoiceItemDTO;
import com.fiap.transactionsAPI.entity.InvoiceItemEntity;

public interface InvoiceItemService {

    public void addItemToInvoice(InvoiceItemDTO purchaseItem, CardDTO cardDTO);

    public InvoiceItemEntity insert(InvoiceItemDTO purchaseItem);
}
