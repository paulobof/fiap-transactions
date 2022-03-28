package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.CardDTO;
import com.fiap.transactionsAPI.dto.InvoiceItemDTO;
import com.fiap.transactionsAPI.entity.CardEntity;
import com.fiap.transactionsAPI.entity.InvoiceEntity;

import java.util.List;
import java.util.Optional;

public interface CardService {

    public Optional<CardEntity> findCard(CardDTO cardDTO);

    InvoiceEntity recoverInvoice(CardDTO cardDTO);

    InvoiceEntity createOrUpdateInvoice(List<InvoiceEntity> invoiceEntityList, InvoiceItemDTO invoiceItemDTO);

    CardEntity update(CardEntity cardEntity, InvoiceEntity updatedInvoice);
}
