package com.fiap.transactionsAPI.config;

import com.fiap.transactionsAPI.entity.CardAccountEntity;
import com.fiap.transactionsAPI.entity.CardEntity;
import com.fiap.transactionsAPI.entity.InvoiceEntity;
import com.fiap.transactionsAPI.entity.InvoiceItemEntity;
import com.fiap.transactionsAPI.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    private CardRepository cardRepository;
    private CardAccountRepository cardAccountRepository;
    private InvoiceRepository invoiceRepository;
    private InvoiceItemRepository invoiceItemRepository;
    private StudentRepository studentRepository;

    public Instantiation(CardRepository cardRepository,
                         CardAccountRepository cardAccountRepository,
                         InvoiceRepository invoiceRepository,
                         InvoiceItemRepository invoiceItemRepository,
                         StudentRepository studentRepository){

        this.cardRepository = cardRepository;
        this.cardAccountRepository = cardAccountRepository;
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemRepository = invoiceItemRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        cardRepository.deleteAll();
        invoiceRepository.deleteAll();
        invoiceRepository.deleteAll();
        cardAccountRepository.deleteAll();


        InvoiceEntity invoiceEntity = new InvoiceEntity();
        InvoiceItemEntity invoiceItemEntity1 = new InvoiceItemEntity(LocalDate.of(2022, 3, 10), "Amazon", 250.0);
        InvoiceItemEntity invoiceItemEntity2 = new InvoiceItemEntity(LocalDate.of(2022, 3, 10), "Mercado Livre", 250.0);
        List<InvoiceItemEntity> invoiceItemEntityList = Arrays.asList(invoiceItemEntity1, invoiceItemEntity2);

        invoiceItemEntityList = invoiceItemRepository.saveAll(invoiceItemEntityList);


        invoiceEntity.setBarcode("84690000000 - 7 85990109011 - 7 00466059035 - 9 60114802628 - 4");
        invoiceEntity.setClosingDate(LocalDate.of(2022, 4, 5));
        invoiceEntity.setDueDate(LocalDate.of(2022, 4, 10));
        invoiceEntity.setFullValue(500.0);
        invoiceEntity.setIssuanceDate(LocalDate.of(2022, 4, 1));
        invoiceEntity.setMinimalValue(100.0);
        invoiceEntity.setInvoiceItemEntity(invoiceItemEntityList);

        invoiceEntity = invoiceRepository.save(invoiceEntity);

        CardAccountEntity cardAccountEntity = getCardAccountEntity();

        CardEntity cardEntity = getCardEntity(cardAccountEntity, invoiceEntity);


//        cardRepository.save(cardEntity);
    }


    private CardEntity getCardEntity(CardAccountEntity cardAccountEntity, InvoiceEntity invoiceEntity){
        CardEntity cardEntity = new CardEntity();
        List<InvoiceEntity> invoiceEntityList = new ArrayList();
        invoiceEntityList.add(invoiceEntity);
        cardEntity.setName("WESLEY GUIMARAES");
        cardEntity.setCardFlag("VISA");
        cardEntity.setExpirationDate(LocalDate.of(2029, 10, 30));
        cardEntity.setSecurityCode(429);
        cardEntity.setCardNumber(8907654321123L);
        cardEntity.setCardAccount(cardAccountEntity);
        cardEntity.setInvoiceEntityList(invoiceEntityList);

        cardEntity = cardRepository.save(cardEntity);

        return cardEntity;
    }


    private CardAccountEntity getCardAccountEntity() {
        CardAccountEntity cardAccountEntity = new CardAccountEntity();

        cardAccountEntity.setAccountBalance(1000.0);
        cardAccountEntity.setAccountLimit(1000.0);

        cardAccountEntity = cardAccountRepository.save(cardAccountEntity);
        return cardAccountEntity;
    }
}
