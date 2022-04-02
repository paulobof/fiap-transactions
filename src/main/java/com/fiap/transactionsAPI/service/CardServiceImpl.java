package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.CardDTO;
import com.fiap.transactionsAPI.dto.InvoiceItemDTO;
import com.fiap.transactionsAPI.dto.StudentDTO;
import com.fiap.transactionsAPI.entity.CardAccountEntity;
import com.fiap.transactionsAPI.entity.CardEntity;
import com.fiap.transactionsAPI.entity.InvoiceEntity;
import com.fiap.transactionsAPI.entity.InvoiceItemEntity;
import com.fiap.transactionsAPI.enums.CardFlagEnum;
import com.fiap.transactionsAPI.repository.CardRepository;
import com.fiap.transactionsAPI.utils.Constants;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final InvoiceService invoiceService;
    private final InvoiceItemService invoiceItemService;
    private final CardAccountService cardAccountService;
    private final StudentService studentService;
    private final Random random = new Random();

    public CardServiceImpl(CardRepository cardRepository,
                           InvoiceService invoiceService,
                           InvoiceItemService invoiceItemService,
                           CardAccountService cardAccountService,
                           StudentService studentService) {
        this.cardRepository = cardRepository;
        this.invoiceService = invoiceService;
        this.invoiceItemService = invoiceItemService;
        this.cardAccountService = cardAccountService;
        this.studentService = studentService;
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
        InvoiceEntity currentInvoice;
        List<InvoiceEntity> currentInvoiceList = new ArrayList<>();
        if (invoiceEntityList != null)
            currentInvoiceList = getCurrentInvoiceList(invoiceEntityList);

        InvoiceItemEntity invoiceItemPersisted = invoiceItemService.insert(purchaseItem);

        if (!currentInvoiceList.isEmpty())
            currentInvoice = invoiceService.update(currentInvoiceList.get(0), invoiceItemPersisted);
        else
            currentInvoice = invoiceService.create(invoiceItemPersisted);


        return currentInvoice;
    }
    @Override
    public List<InvoiceEntity> getCurrentInvoiceList(List<InvoiceEntity> invoiceEntityList) {
        List<InvoiceEntity> currentInvoiceList;
        LocalDate today = LocalDate.now();
        int currentOrNextMonth = today.getDayOfMonth() > Constants.CLOSING_DAY ? Constants.NEXT_MONTH : Constants.CURRENT_MONTH;
        currentInvoiceList = invoiceEntityList.stream()
                .filter(invoiceEntity -> (invoiceEntity.getIssuanceDate().getYear() == today.getYear())
                        && (invoiceEntity.getIssuanceDate().getMonthValue() == today.getMonthValue() + currentOrNextMonth))
                .collect(Collectors.toList());
        return currentInvoiceList;
    }

    @Override
    public CardEntity update(CardEntity cardEntity, InvoiceEntity updatedInvoice, InvoiceItemDTO purchaseItem) {
        if (cardEntity.getInvoiceEntityList() == null)
            cardEntity.setInvoiceEntityList(new ArrayList<>());

        cardEntity.getInvoiceEntityList().add(updatedInvoice);

        CardAccountEntity cardAccountEntity = cardEntity.getCardAccount();
        cardAccountEntity.setAccountBalance(cardAccountEntity.getAccountBalance() - purchaseItem.getPurchaseValue());
        cardAccountService.update(cardAccountEntity);
        return cardRepository.save(cardEntity);
    }

    @Override
    public StudentDTO generateCard(Long ra) {
        StudentDTO studentDTO = studentService.findByRa(ra);
        if (studentDTO.getCard() == null) {
            studentDTO.setCard(generateCard(studentDTO, new CardEntity()));

        }
        return studentService.update(studentDTO);
    }

    private CardEntity generateCard(StudentDTO studentDTO, CardEntity cardEntity) {
        cardEntity.setCardNumber(generateCardNumber());
        cardEntity.setCardAccount(generateCardAccount());
        cardEntity.setName(studentDTO.getName().toUpperCase());
        cardEntity.setSecurityCode(generateSecurityCod());
        cardEntity.setCardFlag(generateCardFlag());
        cardEntity.setExpirationDate(generateExpirationDate());
        return cardRepository.insert(cardEntity);
    }

    private LocalDate generateExpirationDate() {
        return LocalDate.now().plus(6, ChronoUnit.YEARS);
    }

    private CardFlagEnum generateCardFlag() {
        return CardFlagEnum.VISA;
    }

    private Integer generateSecurityCod() {
        StringBuilder sb = new StringBuilder();
        while (sb.length() != 3) sb.append(random.nextInt(9) + 1);
        return Integer.valueOf(sb.toString());
    }

    private CardAccountEntity generateCardAccount() {
        CardAccountEntity cardAccountEntity = new CardAccountEntity(1000.0, 1000.0);
        return new CardAccountEntity(cardAccountService.update(cardAccountEntity));
    }

    private Long generateCardNumber() {
        StringBuilder sb = new StringBuilder();
        while (sb.length() != 16) sb.append(random.nextInt(9) + 1);
        return Long.valueOf(sb.toString());
    }
}
