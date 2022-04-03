package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.CardDTO;
import com.fiap.transactionsAPI.dto.InvoiceItemDTO;
import com.fiap.transactionsAPI.dto.StudentDTO;
import com.fiap.transactionsAPI.entity.CardEntity;
import com.fiap.transactionsAPI.entity.InvoiceEntity;

import java.util.List;
import java.util.Optional;

public interface CardService {

    public Optional<CardEntity> findCard(Long cardNumber);

    InvoiceEntity recoverInvoice(CardDTO cardDTO);

    InvoiceEntity createOrUpdateInvoice(List<InvoiceEntity> invoiceEntityList, InvoiceItemDTO invoiceItemDTO);

    List<InvoiceEntity> getCurrentInvoiceList(List<InvoiceEntity> invoiceEntityList);

    CardEntity update(CardEntity cardEntity, InvoiceEntity updatedInvoice, InvoiceItemDTO purchaseItem);

    StudentDTO generateCard(Long ra);

    void delete(Long cardNumber);


}
