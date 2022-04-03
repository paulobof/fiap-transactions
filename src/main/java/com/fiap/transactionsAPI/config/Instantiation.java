package com.fiap.transactionsAPI.config;

import com.fiap.transactionsAPI.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

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

        studentRepository.deleteAll();
        cardRepository.deleteAll();
        invoiceRepository.deleteAll();
        invoiceItemRepository.deleteAll();
        cardAccountRepository.deleteAll();

    }

}
