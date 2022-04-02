package com.fiap.transactionsAPI.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Mailer {

    private final JavaMailSender mailSender;

    public Mailer(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendEmail(MailMessage mailMessage){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(mailMessage.getSender());
        simpleMailMessage.setTo(mailMessage.getRecipient());
        simpleMailMessage.setSubject(mailMessage.getSubject());
        simpleMailMessage.setText(mailMessage.getBody());

        mailSender.send(simpleMailMessage);
    }

}
