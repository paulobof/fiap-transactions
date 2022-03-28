package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.CardDTO;
import com.fiap.transactionsAPI.dto.InvoiceItemDTO;
import com.fiap.transactionsAPI.entity.InvoiceItemEntity;
import com.fiap.transactionsAPI.repository.InvoiceItemRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;

    public InvoiceItemServiceImpl(InvoiceItemRepository invoiceItemRepository){
        this.invoiceItemRepository = invoiceItemRepository;
    }



    @Override
    public void addItemToInvoice(InvoiceItemDTO purchaseItem, CardDTO cardDTO) {
    }

    @Override
    public InvoiceItemEntity insert(InvoiceItemDTO purchaseItem) {

        return invoiceItemRepository.insert(new InvoiceItemEntity(purchaseItem));
    }
}
