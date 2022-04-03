package com.fiap.transactionsAPI.mail;

import com.fiap.transactionsAPI.utils.Constants;

public class MailMessage {


    private String sender;
    private String recipient;
    private String subject;
    private String body;

    public MailMessage(String sender, String recipient, String subject, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    public MailMessage() {

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String salutation(String name){
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.OLÁ_).append(name).append(",").append(Constants.BR)
                .append(Constants.BR)
                .append(Constants.MSG_FATURA_MES_ATUAL).append(Constants.BR);

        return sb.toString();

    }
}
