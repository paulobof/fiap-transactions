package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.ReportDTO;
import com.fiap.transactionsAPI.dto.StudentDTO;
import com.fiap.transactionsAPI.entity.CardAccountEntity;
import com.fiap.transactionsAPI.entity.InvoiceEntity;
import com.fiap.transactionsAPI.enums.ResultEnum;
import com.fiap.transactionsAPI.mail.MailMessage;
import com.fiap.transactionsAPI.mail.Mailer;
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
    public static final String LIMITE_DO_CARTAO = "Limite do cartão: ";
    public static final String VALOR_TOTAL_DA_FATURA = "Valor total da Fatura: ";
    public static final String SALDO_DISPONIVEL = "Saldo Disponível: ";
    public static final String FATURA_ABERTA = "Fatura aberta";
    public static final String FATURA_FECHADA = "Fatura fechada";
    public static final String EXTRATO_MES_ATUAL_FIAP_CARD = "Extrato mês atual - FIAP Card";
    public static final String MAIL_APP = "transaction.fiap@gmail.com";

    private final StudentService studentService;
    private final Mailer mailer;

    private StringBuilder stringBuilder;
    final LocalDate today = LocalDate.now();

    private ReportDTO reportDTO = new ReportDTO();

    String closedInvoice = FATURA_ABERTA;

    private MailMessage mailMessage = new MailMessage();

    public ReportServiceImpl(StudentService studentService, Mailer mailer) {

        this.studentService = studentService;
        this.mailer = mailer;
    }


    @Override
    public ReportDTO generateReport(Long ra) {
        StringBuilder sb = new StringBuilder();
        StudentDTO studentDTO = studentService.findByRa(ra);
        if (!ObjectUtils.isEmpty(studentDTO.getCard())){
            CardAccountEntity cardAccount = studentDTO.getCard().getCardAccount();
            List<InvoiceEntity> invoiceEntityList = studentDTO.getCard().getInvoiceEntityList();
            sb.append(mailMessage.salutation(studentDTO.getName()));
            if (!ObjectUtils.isEmpty(studentDTO.getCard().getInvoiceEntityList())){
                InvoiceEntity currentInvoice = invoiceEntityList.stream()
                        .filter(invoiceEntity -> invoiceEntity.getIssuanceDate().getMonthValue() == today.getMonthValue())
                        .collect(Collectors.toList())
                        .get(0);

                if (!ObjectUtils.isEmpty(currentInvoice)) {
                            sb.append(invoiceItemReport(currentInvoice)).append(Constants.BR)
                            .append(LIMITE_DO_CARTAO).append(cardAccount.getAccountLimit()).append(Constants.BR)
                            .append(VALOR_TOTAL_DA_FATURA).append(cardAccount.getAccountLimit() - cardAccount.getAccountBalance()).append(Constants.BR)
                            .append(SALDO_DISPONIVEL).append(cardAccount.getAccountBalance()).append(Constants.BR)
                            .append(closedInvoice);
                }else{
                    sb.append(LIMITE_DO_CARTAO).append(cardAccount.getAccountLimit()).append(Constants.BR)
                            .append(VALOR_TOTAL_DA_FATURA).append(cardAccount.getAccountLimit() - cardAccount.getAccountBalance()).append(Constants.BR)
                            .append(SALDO_DISPONIVEL).append(cardAccount.getAccountBalance()).append(Constants.BR)
                            .append(closedInvoice);
                }

//                mailMessage.setSubject(EXTRATO_MES_ATUAL_FIAP_CARD);
//                mailMessage.setBody(sb.toString());
//                mailMessage.setSender(MAIL_APP);
//                mailMessage.setRecipient(studentDTO.getEmail());
//                mailer.sendEmail(mailMessage);
//                reportDTO.setMessageSent(ResultEnum.SUCCESS);
//                reportDTO.setMailMessage(mailMessage);


            }else{
                sb.append(LIMITE_DO_CARTAO).append(cardAccount.getAccountLimit()).append(Constants.BR)
                        .append(VALOR_TOTAL_DA_FATURA).append(cardAccount.getAccountLimit() - cardAccount.getAccountBalance()).append(Constants.BR)
                        .append(SALDO_DISPONIVEL).append(cardAccount.getAccountBalance()).append(Constants.BR)
                        .append(closedInvoice);
            }
            mailMessage.setSubject(EXTRATO_MES_ATUAL_FIAP_CARD);
            mailMessage.setBody(sb.toString());
            mailMessage.setSender(MAIL_APP);
            mailMessage.setRecipient(studentDTO.getEmail());
            mailer.sendEmail(mailMessage);
            reportDTO.setMessageSent(ResultEnum.SUCCESS);
            reportDTO.setMailMessage(mailMessage);

        }else{
            reportDTO.setMessageSent(ResultEnum.FAILED);
        }

        return reportDTO;
    }

    private String invoiceItemReport(InvoiceEntity currentInvoice) {
        stringBuilder = new StringBuilder();
        closedInvoice = today.isBefore(currentInvoice.getClosingDate()) ? FATURA_ABERTA : FATURA_FECHADA;
        stringBuilder.append(Constants.COMPRAS_REALIZADAS).append(Constants.BR).append(Constants.BR);
        currentInvoice.getInvoiceItemEntity().stream()
                .forEach(item -> stringBuilder.append("Item: ")
                        .append(currentInvoice.getInvoiceItemEntity().indexOf(item) + 1).append(Constants.BR)
                        .append(DATA_DA_COMPRA).append(item.getPurchaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))).append(Constants.BR)
                        .append(ESTABELECIMENTO).append(item.getEstablishment()).append(Constants.BR)
                        .append(VALOR_DA_COMPRA).append(item.getItemValue()).append(Constants.BR));

        return stringBuilder.toString();
    }
}
