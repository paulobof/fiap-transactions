package com.fiap.transactionsAPI.config;

import com.fiap.transactionsAPI.entity.*;
import com.fiap.transactionsAPI.enums.CardFlagEnum;
import com.fiap.transactionsAPI.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    private final CardRepository cardRepository;
    private final CardAccountRepository cardAccountRepository;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemRepository invoiceItemRepository;
    private final StudentRepository studentRepository;

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

//        studentRepository.deleteAll();
//        cardRepository.deleteAll();
        invoiceRepository.deleteAll();
        invoiceItemRepository.deleteAll();
//        cardAccountRepository.deleteAll();


        InvoiceEntity invoiceEntity = new InvoiceEntity();
//        InvoiceItemEntity invoiceItemEntity1 = new InvoiceItemEntity(LocalDate.of(2022, 3, 10), "Amazon", 250.0);
//        InvoiceItemEntity invoiceItemEntity2 = new InvoiceItemEntity(LocalDate.of(2022, 3, 10), "Mercado Livre", 250.0);
//        List<InvoiceItemEntity> invoiceItemEntityList = Arrays.asList(invoiceItemEntity1, invoiceItemEntity2);

//        invoiceItemEntityList = invoiceItemRepository.saveAll(invoiceItemEntityList);


//        invoiceEntity.setBarcode("84690000000 - 7 85990109011 - 7 00466059035 - 9 60114802628 - 4");
//        invoiceEntity.setClosingDate(LocalDate.of(2022, 4, 5));
//        invoiceEntity.setDueDate(LocalDate.of(2022, 4, 10));
//        invoiceEntity.setFullValue(500.0);
//        invoiceEntity.setIssuanceDate(LocalDate.of(2022, 4, 1));
//        invoiceEntity.setMinimalValue(100.0);
//        invoiceEntity.setInvoiceItemEntity(new ArrayList<InvoiceItemEntity>());
//
//        invoiceEntity = invoiceRepository.save(invoiceEntity);

        CardAccountEntity cardAccountEntity = getCardAccountEntity();

        CardEntity cardEntity = getCardEntity(cardAccountEntity, invoiceEntity);

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName("Wesley Guimarães");
        studentEntity.setEmail("wees.guimaraes@gmail.com");
        studentEntity.setRa(343539L);
        studentEntity.setCard(cardEntity);

//        studentRepository.save(studentEntity);


//        cardRepository.save(cardEntity);
    }


    private CardEntity getCardEntity(CardAccountEntity cardAccountEntity, InvoiceEntity invoiceEntity){
        CardEntity cardEntity = new CardEntity();
        List<InvoiceEntity> invoiceEntityList = new ArrayList<>();
        invoiceEntityList.add(invoiceEntity);
        cardEntity.setName("WESLEY GUIMARAES");
        cardEntity.setCardFlag(CardFlagEnum.VISA);
        cardEntity.setExpirationDate(LocalDate.of(2029, 10, 30));
        cardEntity.setSecurityCode(429);
        cardEntity.setCardNumber(8907654321123L);
        cardEntity.setCardAccount(cardAccountEntity);
//        cardEntity.setInvoiceEntityList(invoiceEntityList);

//        cardEntity = cardRepository.save(cardEntity);

        return cardEntity;
    }


    private CardAccountEntity getCardAccountEntity() {
        CardAccountEntity cardAccountEntity = new CardAccountEntity();

        cardAccountEntity.setAccountBalance(1000.0);
        cardAccountEntity.setAccountLimit(1000.0);

//        cardAccountEntity = cardAccountRepository.save(cardAccountEntity);
        return cardAccountEntity;
    }
}
