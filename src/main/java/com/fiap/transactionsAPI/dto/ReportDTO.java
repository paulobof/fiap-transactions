package com.fiap.transactionsAPI.dto;

import com.fiap.transactionsAPI.enums.ResultEnum;
import com.fiap.transactionsAPI.mail.MailMessage;

public class ReportDTO {

    private ResultEnum messageSent;
    private MailMessage mailMessage;

    public ReportDTO(ResultEnum messageSent, MailMessage mailMessage) {
        this.messageSent = messageSent;
        this.mailMessage = mailMessage;
    }

    public ReportDTO() {
    }

    public ResultEnum getMessageSent() {
        return messageSent;
    }

    public void setMessageSent(ResultEnum messageSent) {
        this.messageSent = messageSent;
    }

    public MailMessage getMailMessage() {
        return mailMessage;
    }

    public void setMailMessage(MailMessage mailMessage) {
        this.mailMessage = mailMessage;
    }
}
