package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.entity.InvoiceEntity;
import com.fiap.transactionsAPI.entity.InvoiceItemEntity;
import com.fiap.transactionsAPI.repository.InvoiceRepository;
import com.fiap.transactionsAPI.utils.Constants;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public InvoiceEntity create(InvoiceItemEntity purchaseItem) {
        LocalDate currentDate = LocalDate.now();

        InvoiceEntity newInvoiceEntity = buildInvoice(purchaseItem, new InvoiceEntity(), currentDate);

        return invoiceRepository.insert(newInvoiceEntity);
    }

    @Override
    public InvoiceEntity update(InvoiceEntity invoiceEntity, InvoiceItemEntity invoiceItemEntity) {
        invoiceEntity.getInvoiceItemEntity().add(invoiceItemEntity);
        invoiceEntity.setFullValue(invoiceEntity.getFullValue() + invoiceItemEntity.getItemValue());
        invoiceEntity.setMinimalValue(invoiceEntity.getFullValue() * 0.15);
        return invoiceRepository.save(invoiceEntity);
    }

    private InvoiceEntity buildInvoice(InvoiceItemEntity purchaseItem, InvoiceEntity invoiceEntity, LocalDate currentDate) {
        List<InvoiceItemEntity> invoiceItemEntityList = new ArrayList<>();
        invoiceItemEntityList.add(purchaseItem);

        if (currentDate.getDayOfMonth() > Constants.CLOSING_DAY)
            putDates(invoiceEntity, currentDate, Constants.NEXT_MONTH);
        else
            putDates(invoiceEntity, currentDate, Constants.CURRENT_MONTH);

        invoiceEntity.setBarcode(barcodeGenerate());
        invoiceEntity.setFullValue(purchaseItem.getItemValue());
        invoiceEntity.setMinimalValue(invoiceEntity.getFullValue() * 0.15);

        invoiceEntity.setInvoiceItemEntity(invoiceItemEntityList);

        return invoiceEntity;
    }

    private void putDates(InvoiceEntity invoiceEntity, LocalDate currentDate, int currentOrNextMonth) {
        invoiceEntity.setIssuanceDate(LocalDate.of(currentDate.getYear(),
                currentDate.getMonth().getValue() + currentOrNextMonth,
                1));

        invoiceEntity.setClosingDate(LocalDate.of(currentDate.getYear(),
                currentDate.getMonthValue() + currentOrNextMonth,
                Constants.CLOSING_DAY));

        invoiceEntity.setDueDate(LocalDate.of(currentDate.getYear(),
                currentDate.getMonth().getValue() + currentOrNextMonth,
                Constants.DUE_DATE));
    }

    private String barcodeGenerate() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int counter = 0;
        while (counter != 5) {
            sb.append(random.nextInt(99999) + 10000).append(" ");
            counter++;
        }
        return sb.substring(0, sb.length() - 1);
    }
}
