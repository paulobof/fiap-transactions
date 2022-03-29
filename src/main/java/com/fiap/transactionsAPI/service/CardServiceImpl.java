package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.CardDTO;
import com.fiap.transactionsAPI.dto.InvoiceItemDTO;
import com.fiap.transactionsAPI.entity.CardEntity;
import com.fiap.transactionsAPI.entity.InvoiceEntity;
import com.fiap.transactionsAPI.entity.InvoiceItemEntity;
import com.fiap.transactionsAPI.repository.CardRepository;
import com.fiap.transactionsAPI.utils.Constants;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;
    private final InvoiceService invoiceService;
    private final InvoiceItemService invoiceItemService;

    public CardServiceImpl(CardRepository cardRepository,
                           InvoiceService invoiceService,
                           InvoiceItemService invoiceItemService){
        this.cardRepository = cardRepository;
        this.invoiceService = invoiceService;
        this.invoiceItemService = invoiceItemService;
    }

    @Override
    public Optional<CardEntity> findCard(CardDTO cardDTO) {
        return cardRepository.findByCardNumber(cardDTO.getCardNumber());
    }

    @Override
    public InvoiceEntity recoverInvoice(CardDTO cardDTO) {

        return null;
    }

    @Override
    public InvoiceEntity createOrUpdateInvoice(List<InvoiceEntity> invoiceEntityList, InvoiceItemDTO purchaseItem) {
        LocalDate today = LocalDate.now();
        InvoiceEntity currentInvoice;
        List<InvoiceEntity> currentInvoiceList = new ArrayList<>();
        if (invoiceEntityList != null){
            int currentOrNextMonth = today.getDayOfMonth() > Constants.CLOSING_DAY ? Constants.NEXT_MONTH : Constants.CURRENT_MONTH;
            currentInvoiceList = invoiceEntityList.stream()
                    .filter(invoiceEntity -> (invoiceEntity.getIssuanceDate().getYear() == today.getYear())
                            && (invoiceEntity.getIssuanceDate().getMonthValue() == today.getMonthValue() + currentOrNextMonth))
                    .collect(Collectors.toList());
        }
        InvoiceItemEntity invoiceItemPersisted = invoiceItemService.insert(purchaseItem);

        if (!currentInvoiceList.isEmpty())
            currentInvoice = invoiceService.update(currentInvoiceList.get(0), invoiceItemPersisted);
        else
            currentInvoice = invoiceService.create(invoiceItemPersisted);


        return currentInvoice;
    }

    @Override
    public CardEntity update(CardEntity cardEntity, InvoiceEntity updatedInvoice) {
        if (cardEntity.getInvoiceEntityList() == null)
            cardEntity.setInvoiceEntityList(new ArrayList<>());

        cardEntity.getInvoiceEntityList().add(updatedInvoice);
        return cardRepository.save(cardEntity);
    }
}
