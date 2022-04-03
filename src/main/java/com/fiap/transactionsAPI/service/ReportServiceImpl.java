package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.StudentDTO;
import com.fiap.transactionsAPI.entity.InvoiceEntity;
import com.fiap.transactionsAPI.mail.MailMessage;
import com.fiap.transactionsAPI.utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    public static final String DATA_DA_COMPRA = "Data da compra: ";
    public static final String ESTABELECIMENTO = "Estabelecimento: ";
    public static final String VALOR_DA_COMPRA = "Valor da compra: ";
    private final StudentService studentService;
    private StringBuilder stringBuilder;

    private MailMessage mailMessage = new MailMessage();

    public ReportServiceImpl(StudentService studentService){

        this.studentService = studentService;
    }

    @Override
    public String generateReport(Long ra) {

        StringBuilder sb = new StringBuilder();
        LocalDate today = LocalDate.now();
        StudentDTO studentDTO = studentService.findByRa(ra);
        if (!ObjectUtils.isEmpty(studentDTO.getCard()) &&
        !ObjectUtils.isEmpty(studentDTO.getCard().getInvoiceEntityList())){
            List<InvoiceEntity> invoiceEntityList = studentDTO.getCard().getInvoiceEntityList();
            InvoiceEntity currentInvoice = invoiceEntityList.stream()
                    .filter(invoiceEntity -> invoiceEntity.getIssuanceDate().getMonthValue() == today.getMonthValue())
                    .collect(Collectors.toList())
                    .get(0);
            sb.append(mailMessage.salutation(studentDTO.getName()))
                    .append(invoiceReport(currentInvoice));

        }

        System.out.println(sb);

        return null;
    }

    private String invoiceReport(InvoiceEntity currentInvoice) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.COMPRAS_REALIZADAS).append(Constants.BR);
        currentInvoice.getInvoiceItemEntity().stream()
                .forEach(item -> stringBuilder.append("Item: ")
                        .append(currentInvoice.getInvoiceItemEntity().indexOf(item) + 1).append(Constants.BR)
                        .append(DATA_DA_COMPRA).append(item.getPurchaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"))).append(Constants.BR)
                        .append(ESTABELECIMENTO).append(item.getEstablishment()).append(Constants.BR)
                        .append(VALOR_DA_COMPRA).append(item.getItemValue()).append(Constants.BR));

        return stringBuilder.toString();
    }
}
