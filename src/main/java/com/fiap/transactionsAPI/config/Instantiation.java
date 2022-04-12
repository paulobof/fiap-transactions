package com.fiap.transactionsAPI.config;

import com.fiap.transactionsAPI.repository.*;
import com.fiap.transactionsAPI.service.CardService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner {

//    private final CardRepository cardRepository;
//    private final CardAccountRepository cardAccountRepository;
//    private final InvoiceRepository invoiceRepository;
//    private final InvoiceItemRepository invoiceItemRepository;
    private final CardService cardService;
    private final StudentRepository studentRepository;

//    CardRepository cardRepository,
//    CardAccountRepository cardAccountRepository,
//    InvoiceRepository invoiceRepository,
//    InvoiceItemRepository invoiceItemRepository,

    public Instantiation(StudentRepository studentRepository,
                         CardService cardService){

//        this.cardRepository = cardRepository;
//        this.cardAccountRepository = cardAccountRepository;
//        this.invoiceRepository = invoiceRepository;
//        this.invoiceItemRepository = invoiceItemRepository;
        this.cardService = cardService;
        this.studentRepository = studentRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println("Instantiation ok.");

//        List<StudentEntity> studentsWithoutCard = studentRepository.getStudentsWithoutCard();
//
//        studentsWithoutCard.stream().forEach(s -> cardService.generateCard(s.getRa()));

    }

}
